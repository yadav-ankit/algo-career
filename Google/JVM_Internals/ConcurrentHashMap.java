

import java.util.concurrent.locks.ReentrantLock;

class MyConcurrentHashMap<K,V> {

    private static final int CAPACITY = 16;

    private Node<K,V>[] buckets;
    private ReentrantLock[] locks;

    public MyConcurrentHashMap(){

        buckets = new Node[CAPACITY];
        locks = new ReentrantLock[CAPACITY];

        for(int i=0;i<CAPACITY;i++)
            locks[i] = new ReentrantLock();
    }


    private int getBucket(K key){
        return Math.abs(key.hashCode()) % CAPACITY;
    }


   public void put(K key, V value){

    int bucket = getBucket(key);

    locks[bucket].lock();

    try{

        Node<K,V> head = buckets[bucket];

        // Empty bucket
        if(head == null){
            buckets[bucket] = new Node<>(key, value);
            return;
        }

        Node<K,V> curr = head;

        while(curr != null){

            // Update existing key
            if(curr.key.equals(key)){
                curr.value = value;
                return;
            }

            // reached last node
            if(curr.next == null)
                break;

            curr = curr.next;
        }

        // append at end
        curr.next = new Node<>(key, value);

    } finally{
        locks[bucket].unlock();
    }
}



    public V get(K key){

        int bucket = getBucket(key);

        locks[bucket].lock();

        try{

            Node<K,V> head = buckets[bucket];

            while(head != null){

                if(head.key.equals(key))
                    return head.value;

                head = head.next;
            }

            return null;

        } finally{
            locks[bucket].unlock();
        }
    }



    public void remove(K key){

        int bucket = getBucket(key);

        locks[bucket].lock();

        try{

            Node<K,V> curr = buckets[bucket];
            Node<K,V> prev = null;

            while(curr != null){

                if(curr.key.equals(key)){

                    if(prev == null)
                        buckets[bucket] = curr.next;
                    else
                        prev.next = curr.next;

                    return;
                }

                prev = curr;
                curr = curr.next;
            }

        } finally{
            locks[bucket].unlock();
        }
    }
}

