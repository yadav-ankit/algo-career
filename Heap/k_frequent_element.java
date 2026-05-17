
Map<Integer, Integer> freq = new HashMap<>();

for (int n : nums)
    freq.put(n, freq.getOrDefault(n, 0) + 1);

PriorityQueue<Integer> pq =
        new PriorityQueue<>((a, b) -> freq.get(a) - freq.get(b));

for (int key : freq.keySet()) {
    pq.offer(key);

    if (pq.size() > k)
        pq.poll();
}
