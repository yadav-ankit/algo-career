class FreqStack {
    Map<Integer, Integer> freq = new HashMap<>();
    Map<Integer, Deque<Integer>> group = new HashMap<>();
    int maxFreq = 0;

    public void push(int val) {
        int f = freq.getOrDefault(val, 0) + 1;
        freq.put(val, f);

        if (f > maxFreq) {
            maxFreq = f;
        }

        group.computeIfAbsent(f, k -> new ArrayDeque<>()).push(val);
    }

    public int pop() {
        int val = group.get(maxFreq).pop();
        freq.put(val, freq.get(val) - 1);

        if (group.get(maxFreq).isEmpty()) {
            maxFreq--;
        }

        return val;
    }
}


-------------------


  🧩 Data structures used
Structure	Purpose
Map<Integer, Integer> freq	frequency of each value
Map<Integer, Stack<Integer>> group	values grouped by frequency
int maxFreq	highest frequency so far

This ensures:

push → O(1)

pop → O(1)

🪜 How push(x) works

Increment frequency of x

Update maxFreq

Push x onto group[freq(x)]

🪜 How pop() works

Pop from group[maxFreq]

Decrement its frequency

If that stack becomes empty → decrement maxFreq

  
-------------------

Map<Integer, Stack<Integer>> group;

What it REALLY means (plain English)

For each frequency f, keep a stack of numbers that currently have frequency f.
--------------------

## 🧪 Operations so far

```text
push(5)
push(7)
push(5)
push(7)
push(4)
push(5)
```

---

  🧩 Let’s simulate it step by step
Start with empty structures
freq = {}              // value → frequency
group = {}             // frequency → stack of values
maxFreq = 0

🔹 Operation 1: push(5)

freq[5] = 1

maxFreq = 1

add 5 to group[1]

freq:   {5=1}
group:  {
          1 → [5]
        }
maxFreq = 1

🔹 Operation 2: push(7)

freq[7] = 1

add 7 to group[1]

freq:   {5=1, 7=1}
group:  {
          1 → [5, 7]   ← stack (7 is most recent)
        }
maxFreq = 1

🔹 Operation 3: push(5)

freq[5] = 2

maxFreq = 2

add 5 to group[2]

freq:   {5=2, 7=1}
group:  {
          1 → [5, 7]
          2 → [5]
        }
maxFreq = 2


👉 Notice:

5 is now in group[2]

It stays in group[1] historically — we don’t remove it

This is important.

🔹 Operation 4: push(7)

freq[7] = 2

add 7 to group[2]

freq:   {5=2, 7=2}
group:  {
          1 → [5, 7]
          2 → [5, 7]
        }
maxFreq = 2

🔹 Operation 5: push(4)

freq[4] = 1

add 4 to group[1]

freq:   {5=2, 7=2, 4=1}
group:  {
          1 → [5, 7, 4]
          2 → [5, 7]
        }
maxFreq = 2

🔹 Operation 6: push(5)

freq[5] = 3

maxFreq = 3

add 5 to group[3]

freq:   {5=3, 7=2, 4=1}
group:  {
          1 → [5, 7, 4]
          2 → [5, 7]
          3 → [5]
        }
maxFreq = 3
  
## 📦 State BEFORE any pop()

### `freq` (value → frequency)

```text
5 → 3
7 → 2
4 → 1
```

### `group` (frequency → stack)

(top of stack is on the right)

```text
1 → [5, 7, 4]
2 → [5, 7]
3 → [5]
```

### `maxFreq`

```text
3
```

---

# 🔥 POP #1

### Step

* Look at `maxFreq = 3`
* Pop from `group[3]`

### Popped value

```text
5
```

### Update `freq`

```text
5 → 2
7 → 2
4 → 1
```

### Update `group`

```text
1 → [5, 7, 4]
2 → [5, 7]
3 → []      ← empty
```

### Update `maxFreq`

```text
maxFreq = 2
```

---

# 🔥 POP #2

### Step

* Look at `maxFreq = 2`
* Pop from `group[2]`

### Popped value

```text
7
```

### Update `freq`

```text
5 → 2
7 → 1
4 → 1
```

### Update `group`

```text
1 → [5, 7, 4]
2 → [5]
3 → []
```

### `maxFreq`

```text
2
```

---

# 🔥 POP #3

### Step

* Look at `maxFreq = 2`
* Pop from `group[2]`

### Popped value

```text
5
```

### Update `freq`

```text
5 → 1
7 → 1
4 → 1
```

### Update `group`

```text
1 → [5, 7, 4]
2 → []      ← empty
3 → []
```

### Update `maxFreq`

```text
maxFreq = 1
```

---

# 🔥 POP #4

### Step

* Look at `maxFreq = 1`
* Pop from `group[1]`

### Popped value

```text
4
```

### Update `freq`

```text
5 → 1
7 → 1
4 → 0
```

### Update `group`

```text
1 → [5, 7]
2 → []
3 → []
```

### `maxFreq`

```text
1
```

---

## ✅ Final pop sequence

```text
5 → 7 → 5 → 4
```


You’re doing exactly what strong problem-solvers do: slow, precise, and correct 👌
