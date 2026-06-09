
ArrayList Operations Cheat Sheet

  List<Integer> list = new ArrayList<>();

Add
list.add(10);          // add at end
list.add(1, 20);       // add at index
list.addAll(otherList);

Operation	Complexity
add(x)	Amortized O(1)
add(index, x)	O(n)
addAll()	O(m)

  Access
list.get(0);
Operation	Complexity
get(index)	O(1)

  Update
list.set(0, 100);
Operation	Complexity
set(index, x)	O(1)

  Remove
list.remove(0);          // by index
list.remove(Integer.valueOf(10)); // by value
list.clear();
Operation	Complexity
remove(index)	O(n)
remove(value)	O(n)
clear()	O(n)

  Search
list.contains(10);
list.indexOf(10);
list.lastIndexOf(10);
Operation	Complexity
contains()	O(n)
indexOf()	O(n)
lastIndexOf()	O(n)

  Size / Empty
list.size();
list.isEmpty();
Operation	Complexity
size()	O(1)
isEmpty()	O(1)
  
Iteration
for (int x : list) {}

for (int i = 0; i < list.size(); i++) {}

Iterator<Integer> it = list.iterator();

Complexity: O(n)

Conversion
Integer[] arr = list.toArray(new Integer[0]);

List<Integer> list2 = Arrays.asList(1, 2, 3);
Sorting
Collections.sort(list);

list.sort(Integer::compareTo);

Complexity: O(n log n)


  
**Amortized O(1)** means:

> An operation may occasionally take more than O(1), but over many operations the average cost per operation is O(1).

### ArrayList Example

Suppose capacity starts at 4.

```text
[_, _, _, _]
```

Add elements:

```java
list.add(1); // O(1)
list.add(2); // O(1)
list.add(3); // O(1)
list.add(4); // O(1)
```

Now the array is full.

```text
[1, 2, 3, 4]
```

Next add:

```java
list.add(5);
```

Java must:

1. Allocate a bigger array (typically ~1.5x larger).
2. Copy all existing elements.
3. Add the new element.

```text
Old: [1, 2, 3, 4]
New: [1, 2, 3, 4, 5, _, _]
```

This insertion costs **O(n)** because of the copy.

---

### Why is it still Amortized O(1)?

Imagine adding 8 elements:

```text
1 -> O(1)
2 -> O(1)
3 -> O(1)
4 -> O(1)
5 -> O(4)  // resize
6 -> O(1)
7 -> O(1)
8 -> O(1)
```

Total work:

```text
1 + 1 + 1 + 1 + 4 + 1 + 1 + 1 = 11
```

Average:

```text
11 / 8 ≈ 1.4
```

Still constant on average.

As `n` grows, the average cost per insertion stays bounded by a constant.



Interviewers often ask:

> Why is `ArrayList.add()` not O(1)?

Good answer:

> A single insertion can be O(n) when resizing occurs, but since resizing happens infrequently, the amortized complexity over many insertions is O(1).
