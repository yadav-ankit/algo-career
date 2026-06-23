
This is a classic interview problem.

### Requirements

Support all operations in:

* `push(x)` → O(1)
* `pop()` → O(1)
* `getMiddle()` → O(1)
* `deleteMiddle()` → O(1)

A normal stack cannot do this because finding the middle requires traversal.

---

## Idea: Doubly Linked List + Middle Pointer

Maintain:

```java
Node head;   // top of stack
Node mid;    // middle element
int size;
```

### Node

```java
class Node {
    int data;
    Node prev;
    Node next;

    Node(int data) {
        this.data = data;
    }
}
```

---

## Visualization

After pushes:

```text
Top
 ↓
5 <-> 4 <-> 3 <-> 2 <-> 1

size = 5
mid = 3
```

---

## Push

Insert at head.


### Why move mid when size becomes even?

Example:

```text
push(1)

1
mid=1

push(2)

2 1
^
mid=2
```

For even size, conventionally take the first middle.

---

## Pop

Remove head.



---



---

## Delete Middle

Suppose:

```text
5 <-> 4 <-> 3 <-> 2 <-> 1
           ^
          mid
```

Delete node 3 directly.

---

## Complete Class

```java
class SpecialStack {

    private static class Node {
        int data;
        Node prev, next;

        Node(int data) {
            this.data = data;
        }
    }

    private Node head;
    private Node mid;
    private int size;

    public void push(int x) {
        Node node = new Node(x);

        node.next = head;

        if (head != null)
            head.prev = node;

        head = node;
        size++;

        if (size == 1)
            mid = node;
        else if (size % 2 == 0)
            mid = mid.prev;
    }

    public int pop() {
        if (size == 0)
            throw new RuntimeException();

        int val = head.data;

        head = head.next;

        if (head != null)
            head.prev = null;

        size--;

        if (size == 0)
            mid = null;
        else if (size % 2 == 1)
            mid = mid.next;

        return val;
    }

    public int getMiddle() {
        return mid.data;
    }

    public int deleteMiddle() {
        if (size == 0)
            throw new RuntimeException();

        int val = mid.data;

        Node prev = mid.prev;
        Node next = mid.next;

        if (prev != null)
            prev.next = next;

        if (next != null)
            next.prev = prev;

        if (size == 1) {
            head = null;
            mid = null;
        } else if (size % 2 == 1) {
            mid = next;
        } else {
            mid = prev;
        }

        size--;

        return val;
    }
}
```

### Complexity

| Operation    | Time |
| ------------ | ---- |
| push         | O(1) |
| pop          | O(1) |
| getMiddle    | O(1) |
| deleteMiddle | O(1) |

This is the optimal solution typically expected in interviews at companies that ask data-structure design questions.
