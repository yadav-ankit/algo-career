Here's a handy interview-focused Java Data Structures complexity matrix.

| Data Structure       | Access | Search   | Insert   | Delete   |
| -------------------- | ------ | -------- | -------- | -------- |
| Array                | O(1)   | O(n)     | O(n)     | O(n)     |
| ArrayList            | O(1)   | O(n)     | O(1)*    | O(n)     |
| LinkedList           | O(n)   | O(n)     | O(1)**   | O(1)**   |
| Stack                | O(n)   | O(n)     | O(1)     | O(1)     |
| Queue                | O(n)   | O(n)     | O(1)     | O(1)     |
| ArrayDeque           | O(n)   | O(n)     | O(1)     | O(1)     |
| HashMap              | N/A    | O(1)     | O(1)     | O(1)     |
| HashSet              | N/A    | O(1)     | O(1)     | O(1)     |
| LinkedHashMap        | N/A    | O(1)     | O(1)     | O(1)     |
| LinkedHashSet        | N/A    | O(1)     | O(1)     | O(1)     |
| TreeMap              | N/A    | O(log n) | O(log n) | O(log n) |
| TreeSet              | N/A    | O(log n) | O(log n) | O(log n) |
| PriorityQueue (Heap) | N/A    | O(n)     | O(log n) | O(log n) |
| Trie                 | O(L)   | O(L)     | O(L)     | O(L)     |

**Notes**

* `ArrayList` insert is amortized O(1) when appending at the end.
* `LinkedList` insert/delete is O(1) if you already have the node reference.
* `L` = length of the word/key in Trie.

---

### Sorted Operations

| Data Structure | Min      | Max      | Floor/Ceil | Kth Smallest |
| -------------- | -------- | -------- | ---------- | ------------ |
| HashMap        | O(n)     | O(n)     | O(n)       | O(n log n)   |
| TreeMap        | O(log n) | O(log n) | O(log n)   | O(n)         |
| PriorityQueue  | O(1) min | O(n)     | N/A        | O(k log n)   |

---

### Common Interview Choices

| Requirement        | Best Choice          |
| ------------------ | -------------------- |
| Fast lookup        | HashMap              |
| Sorted keys        | TreeMap              |
| LRU Cache          | LinkedHashMap        |
| Top K Elements     | PriorityQueue        |
| Prefix Search      | Trie                 |
| Frequency Counting | HashMap              |
| Sliding Window     | HashMap + ArrayDeque |
| Range Queries      | TreeMap              |
| Median Finder      | Two Heaps            |
| Randomized Set     | HashMap + ArrayList  |

---

### Java Collection Hierarchy Cheatsheet

```text
Collection
|
+-- List
|    +-- ArrayList
|    +-- LinkedList
|
+-- Set
|    +-- HashSet
|    +-- LinkedHashSet
|    +-- TreeSet
|
+-- Queue
     +-- PriorityQueue
     +-- ArrayDeque

Map
|
+-- HashMap
+-- LinkedHashMap
+-- TreeMap
```

### Worst-Case Caveat

| Structure     | Average | Worst    |
| ------------- | ------- | -------- |
| HashMap       | O(1)    | O(log n) |
| HashSet       | O(1)    | O(log n) |
| LinkedHashMap | O(1)    | O(log n) |

Since Java 8, heavily-collided hash buckets become balanced trees, making worst-case operations O(log n) instead of O(n).

For coding interviews, the 10 structures worth memorizing are:

**Array, ArrayList, LinkedList, HashMap, HashSet, TreeMap, TreeSet, PriorityQueue, ArrayDeque, Trie.** These cover ~95% of data structure questions.
