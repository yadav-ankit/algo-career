Given an array `nums` and an integer `k`
👉 count subarrays that contain **exactly `k` odd numbers**.

A subarray is **nice** if:

```
number of odd elements == k
```

---

## 🧠 Key Insight (the winning idea)

Convert the problem into a **prefix sum + hashmap** problem.

### Step 1: Convert array

Turn the array into:

* `1` if number is odd
* `0` if number is even

Example:

```
nums = [1,1,2,1,1], k = 3
oddArray = [1,1,0,1,1]
```

Now the problem becomes:

> Count subarrays with sum = `k`

---

## 🚀 Optimal Approach (O(n) time, O(n) space)

### Java Code

```java
public int numberOfSubarrays(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);

    int prefixSum = 0;
    int count = 0;

    for (int num : nums) {
        prefixSum += (num % 2);

        if (map.containsKey(prefixSum - k)) {
            count += map.get(prefixSum - k);
        }

        map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
    }
    return count;
}
```

---

## 🔍 Example Walkthrough

```
nums = [1,1,2,1,1], k = 3
oddArray = [1,1,0,1,1]
```

Prefix sums:

```
index:      0 1 2 3 4
prefixSum:  1 2 2 3 4
```

When `prefixSum - k` exists → we found valid subarrays.

✅ Final answer = **2**

---

## ⏱️ Complexity

* **Time:** `O(n)`
* **Space:** `O(n)`

---

