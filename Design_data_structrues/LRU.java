1. LRU Cache (Least Recently Used)
Problem

Design a cache with capacity N.

Operations:

get(key)
put(key, value)

Both must be O(1).

When cache is full:

Remove the least recently used item.
Example

Capacity = 2

put(1,10)
put(2,20)

get(1) => 10

Now usage order:

1 (most recent)
2 (least recent)

Then:

put(3,30)

Need to evict 2.

Why HashMap alone doesn't work
map.get(key)

is O(1), but:

Which key was least recently used?

No idea.

Need ordering.

Why Doubly Linked List

Keep nodes ordered by usage.

HEAD <-> Most Recent <-> ... <-> Least Recent <-> TAIL

Whenever:

get(key)

move node to front.

Whenever:

put(key)

move node to front.

Eviction:

tail.prev

is LRU.

O(1).
  
import java.util.HashMap;
import java.util.Map;

class LRUCache {

    private static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> map;

    private final Node head;
    private final Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();

        head = new Node(-1, -1);
        tail = new Node(-1, -1);

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = map.get(key);

        if (node == null) {
            return -1;
        }

        remove(node);
        insertAtFront(node);

        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);

            node.value = value;

            remove(node);
            insertAtFront(node);

            return;
        }

        if (map.size() == capacity) {
            Node lru = tail.prev;

            remove(lru);
            map.remove(lru.key);
        }

        Node node = new Node(key, value);

        insertAtFront(node);
        map.put(key, node);
    }

    private void insertAtFront(Node node) {
        Node next = head.next;

        head.next = node;
        node.prev = head;

        node.next = next;
        next.prev = node;
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}
import java.util.HashMap;
import java.util.Map;

class LRUCache {

    private static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> map;

    private final Node head;
    private final Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();

        head = new Node(-1, -1);
        tail = new Node(-1, -1);

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = map.get(key);

        if (node == null) {
            return -1;
        }

        remove(node);
        insertAtFront(node);

        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);

            node.value = value;

            remove(node);
            insertAtFront(node);

            return;
        }

        if (map.size() == capacity) {
            Node lru = tail.prev;

            remove(lru);
            map.remove(lru.key);
        }

        Node node = new Node(key, value);

        insertAtFront(node);
        map.put(key, node);
    }

    private void insertAtFront(Node node) {
        Node next = head.next;

        head.next = node;
        node.prev = head;

        node.next = next;
        next.prev = node;
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}
