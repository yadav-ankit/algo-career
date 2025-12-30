

# 🔥 PREFIX SUM + HASHMAP — MASTER LIST (JAVA)

---

## 1️⃣ **Subarray Sum Equals K (COUNT)**

### 🧠 Intuition

If

```
prefixSum[j] - prefixSum[i] = k
```

then subarray `(i+1 … j)` has sum = `k`.

We store **frequency of prefix sums**.

### ✅ Java Code

```java
import java.util.HashMap;

public class SubarraySumEqualsK {

    public static int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int sum = 0, count = 0;

        for (int num : nums) {
            sum += num;
            count += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
```

### ⏱ Complexity

* **Time:** `O(n)`
* **Space:** `O(n)`

---

## 2️⃣ **Longest Subarray with Sum = K**

### 🧠 Intuition

Store the **first index** where a prefix sum appears to maximize length.

### ✅ Java Code

```java
import java.util.HashMap;

public class LongestSubarraySumK {

    public static int longestSubarray(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0, maxLen = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            if (sum == k) {
                maxLen = i + 1;
            }

            if (map.containsKey(sum - k)) {
                maxLen = Math.max(maxLen, i - map.get(sum - k));
            }

            map.putIfAbsent(sum, i);
        }
        return maxLen;
    }
}
```

### ⏱ Complexity

* **Time:** `O(n)`
* **Space:** `O(n)`

---

## 3️⃣ **Longest Subarray with Sum = 0**

### 🧠 Intuition

This is just **K = 0** case.

### ✅ Java Code

```java
import java.util.HashMap;

public class LongestZeroSumSubarray {

    public static int longestZeroSum(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0, maxLen = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            if (sum == 0) {
                maxLen = i + 1;
            }

            if (map.containsKey(sum)) {
                maxLen = Math.max(maxLen, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        return maxLen;
    }
}
```

### ⏱ Complexity

* **Time:** `O(n)`
* **Space:** `O(n)`

---

## 4️⃣ **Longest Subarray with Equal 0s and 1s**

### 🧠 Intuition

Convert:

* `0 → -1`
* `1 → +1`

Then find **longest subarray with sum = 0**.



## 5️⃣ **Count Subarrays with Sum Divisible by K**

### 🧠 Intuition

If

```
prefixSum % k repeats
```

then difference is divisible by `k`.

Handle **negative modulo** carefully.

### ✅ Java Code

```java
import java.util.HashMap;

public class SubarrayDivisibleByK {

    public static int subarraysDivByK(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int sum = 0, count = 0;

        for (int num : nums) {
            sum += num;
            int mod = ((sum % k) + k) % k;

            count += map.getOrDefault(mod, 0);
            map.put(mod, map.getOrDefault(mod, 0) + 1);
        }
        return count;
    }
}
```

### ⏱ Complexity

* **Time:** `O(n)`
* **Space:** `O(k)` (at most `k` mod values)



