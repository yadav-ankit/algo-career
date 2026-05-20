# 1️⃣ Next Greater Element (NGE)

## ❓ Problem

For each element, find the **next element to the right** that is **strictly greater**.
If none exists → `-1`.

### Example

```
arr = [4,  5,  2,  10, 8]
NGE = [5, 10, 10, -1, -1]
```

---

## 🧠 Core idea

* Traverse **left → right**
* Maintain a stack of **indices**
* Stack is **monotonically decreasing**

Why?
Because a bigger element can resolve many smaller ones sitting in the stack.


---

## 💻 Java code (NGE)


int[] nextGreater(int[] arr) {
    int n = arr.length;
    int[] nge = new int[n];
    Stack<Integer> st = new Stack<>();

    for (int i = 0; i < n; i++) {
        while (!st.isEmpty() && arr[i] > arr[st.peek()]) {
            nge[st.pop()] = arr[i];
        }
        st.push(i);
    }

    while (!st.isEmpty()) {
        nge[st.pop()] = -1;
    }
    return nge;
}
```

---

# 2️⃣ Next Smaller Element (NSE)

## ❓ Problem

For each element, find the **next element to the right** that is **strictly smaller**.

### Example

```
arr = [4, 5, 2, 10, 8]
NSE = [2, 2, -1, 8, -1]
```

---

## 🧠 Core idea

* Traverse **left → right**
* Stack is **monotonically increasing**

---

## ✅ Algorithm (NSE to the right)

1. For each `i`
2. While stack not empty AND `arr[i] < arr[stack.peek()]`

   * `nse[stack.pop()] = arr[i]`
3. Push `i`
4. Remaining → `-1`

---

## 💻 Java code (NSE)

```java
int[] nextSmaller(int[] arr) {
    int n = arr.length;
    int[] nse = new int[n];
    Stack<Integer> st = new Stack<>();

    for (int i = 0; i < n; i++) {
        while (!st.isEmpty() && arr[i] < arr[st.peek()]) {
            nse[st.pop()] = arr[i];
        }
        st.push(i);
    }

    while (!st.isEmpty()) {
        nse[st.pop()] = -1;
    }
    return nse;
}

