You’re given:

* `k` **sorted linked lists**
* You need to merge them into **one sorted list**


---

## 🪜 Step-by-Step Logic

1. Create a **min heap** based on node value
2. Push the **head of each non-null list**
3. While heap not empty:

   * pop smallest node
   * attach it to result
   * push its `next` node (if exists)

That’s it.

---

## 🧩 Java Code (Clean & Optimal)

```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
            (a, b) -> a.val - b.val
        );

        for (ListNode node : lists) {
            if (node != null) pq.offer(node);
        }

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            curr.next = node;
            curr = curr.next;

            if (node.next != null) {
                pq.offer(node.next);
            }
        }
        return dummy.next;
    }
}
```


* **Time:** `O(N log K)`
* **Space:** `O(log K)` recursion



