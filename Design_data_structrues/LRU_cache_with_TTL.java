an LRU cache where entries also expire after a TTL. Here’s the standard approach, and you can tell me if you meant something else.

Core design

HashMap<K, Node> for O(1) lookup, plus a doubly linked list for recency order (most recent at the head, eviction from the tail).
Each node stores expiresAt = now + ttl.
Lazy expiration on get: if the entry is expired, remove it and return null/miss. An expired read doesn’t count as a hit and doesn’t refresh recency.
Eviction on put: if over capacity, evict the LRU tail. Ideally evict expired entries first, since they’re dead weight.

The tricky part: proactive cleanup


Use a PQ , (a min heap) sorted by expired Time
and creaate a background thread which will raise from sleep ...check heap top element  if expired ..remove it

  keep checking  ...and we know if top element is not expired then others will definately be not 
  so no need to scan rest of the list.



import java.util.*;
import java.util.concurrent.locks.*;

class TTLCache<K, V> implements AutoCloseable {
    private static final class Entry<K, V> {
        final K key; final V value; final long expiresAt; // nanoTime-based
        Entry(K k, V v, long e) { key = k; value = v; expiresAt = e; }
    }

    private final long ttlNanos;
    private final LinkedHashMap<K, Entry<K, V>> map;          // access-order => LRU
    private final PriorityQueue<Entry<K, V>> pq =
        new PriorityQueue<>(Comparator.comparingLong((Entry<K, V> e) -> e.expiresAt));
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition wake = lock.newCondition();
    private final Thread sweeper;
    private volatile boolean running = true;

    TTLCache(int capacity, long ttlMs) {
        this.ttlNanos = ttlMs * 1_000_000L;
        this.map = new LinkedHashMap<>(16, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<K, Entry<K, V>> e) {
                return size() > capacity;
            }
        };
        sweeper = new Thread(this::sweepLoop, "ttl-sweeper");
        sweeper.setDaemon(true);
        sweeper.start();
    }

    V get(K key) {
        lock.lock();
        try {
            Entry<K, V> e = map.get(key);
            if (e == null) return null;
            if (System.nanoTime() - e.expiresAt >= 0) {   // lazy check still needed
                map.remove(key, e);
                return null;
            }
            return e.value;
        } finally { lock.unlock(); }
    }

    void put(K key, V value) {
        lock.lock();
        try {
            Entry<K, V> e = new Entry<>(key, value, System.nanoTime() + ttlNanos);
            map.put(key, e);
            pq.add(e);
            if (pq.peek() == e) wake.signal();   // new earliest deadline, so re-sleep shorter
        } finally { lock.unlock(); }
    }

    private void sweepLoop() {
        lock.lock();
        try {
            while (running) {
                Entry<K, V> head = pq.peek();
                if (head == null) { wake.await(); continue; }
                long wait = head.expiresAt - System.nanoTime();
                if (wait <= 0) {
                    pq.poll();
                    map.remove(head.key, head);  // identity match; skips stale entries
                } else {
                    wake.awaitNanos(wait);
                }
            }
        } catch (InterruptedException ignored) {
        } finally { lock.unlock(); }
    }

    @Override public void close() {
        running = false;
        lock.lock();
        try { wake.signalAll(); } finally { lock.unlock(); }
        sweeper.interrupt();
    }
}
