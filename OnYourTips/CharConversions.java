
## 🔤 ASCII Basics (must know)

char ch[] = my_string.charArray();

for(Char ch : ch[]){

}
  
In Java:


'a' = 97
'z' = 122

So:


char c = 'd';
int x = c - 'a';   // 3


This converts a character into **0–25** range.

---

## ✅ ADD 3 to `'d'`

```java
char c = 'd';

char result = (char) ((c - 'a' + 3) % 26 + 'a');

System.out.println(result); // g
```

Explanation:

```
'd' → 3
3 + 3 = 6
6 → 'g'
```

---

## ✅ SUBTRACT 4 from `'m'`

### ⚠️ Important: subtraction can go negative

```java
char c = 'm';

int shift = (c - 'a' - 4) % 26;
if (shift < 0) shift += 26;

char result = (char) (shift + 'a');

System.out.println(result); // i
```

Explanation:

```
'm' → 12
12 - 4 = 8
8 → 'i'
```

---

## 🔄 WRAP AROUND EXAMPLES (VERY IMPORTANT)

### `'y' + 4 → 'c'`

```java
char c = 'y';

char result = (char) ((c - 'a' + 4) % 26 + 'a');

System.out.println(result); // c
```

Why?

```
'y' → 24
24 + 4 = 28
28 % 26 = 2
2 → 'c'
```

---

### `'b' - 3 → 'y'`

```java
char c = 'b';

int shift = (c - 'a' - 3) % 26;
if (shift < 0) shift += 26;

char result = (char) (shift + 'a');

System.out.println(result); // y
```

---

## 🔑 GOLDEN FORMULAS (MEMORIZE THESE)

### ➕ Add shift

```java
char newChar = (char) ((c - 'a' + shift) % 26 + 'a');
```

### ➖ Subtract shift

```java
int s = (c - 'a' - shift) % 26;
if (s < 0) s += 26;
char newChar = (char) (s + 'a');
```

---

## 💡 PRO TIP (INTERVIEW SAFE)

Instead of handling add/sub separately:

```java
int netShift = (shift % 26 + 26) % 26;
char newChar = (char) ((c - 'a' + netShift) % 26 + 'a');
```

Works for:

* `+k`
* `-k`
* large values

---
