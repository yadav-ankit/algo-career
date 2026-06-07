Design Randomized Set

This is a classic interview question: Insert Delete GetRandom O(1)

Requirements
boolean insert(int val)
boolean remove(int val)
int getRandom()

All operations must be O(1) on average.

First thought: HashSet
Set<Integer> set = new HashSet<>();

Insert: O(1) ✅

Remove: O(1) ✅

Random element?

getRandom()

❌ Not possible in O(1).

Need indexing.

Second thought: ArrayList
List<Integer> list = new ArrayList<>();

Random:

list.get(randomIndex)

O(1) ✅

Insert at end: O(1) ✅

Remove?

Need to find the element first.

O(n)

❌

Solution

Use both:

ArrayList<Integer> list
HashMap<Integer, Integer> map

where:

map[value] = index in list

Example:

list = [10, 20, 30]

map:
10 -> 0
20 -> 1
30 -> 2
Insert

Insert 40:

list = [10,20,30,40]

map:
10 -> 0
20 -> 1
30 -> 2
40 -> 3

Both O(1).

getRandom

Generate:

int idx = random.nextInt(list.size());

Return:

list.get(idx);

O(1).

Remove (The Trick)

Suppose:

list = [10,20,30,40]

map:
10 -> 0
20 -> 1
30 -> 2
40 -> 3

Remove:

remove(20)

Index:

idx = 1
Bad approach
list.remove(1)

Array shifts:

[10,30,40]

O(n) ❌

Smart approach

Take last element:

40

Overwrite removed position:

[10,40,30,40]

Update map:

40 -> 1

Remove last element:

[10,40,30]

Delete:

20 -> removed

Everything O(1).
