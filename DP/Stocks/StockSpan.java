import java.util.*;

class StockSpan {
    public static int[] calculateSpan(int[] prices) {
        int n = prices.length;
        int[] span = new int[n];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && prices[st.peek()] <= prices[i]) {
                st.pop();
            }

            span[i] = st.isEmpty() ? (i + 1) : (i - st.peek());
            st.push(i);
        }
        return span;
    }

    public static void main(String[] args) {
        int[] prices = {100, 80, 60, 70, 60, 75, 85};
        System.out.println(Arrays.toString(calculateSpan(prices)));
    }
}

🔍 Intuition 

For today’s price:

Look left
Count how many days until you find a strictly greater price
Stop there

My Understanding

ANS = basically at every index to get my answer i want someone to tell me which is
    the FIRST greater then or equal to element then me if i look left

So to get that i keep a stack

Logic is to iterate the array from left
    
    at every index ask stack ---does the top element is less then me ? ..remove it (bcz i want strictly greater)
    & keep removing untill i get my ANS
    ALso since its smaller so aage aane waale elements ko bhi nhi chaahiye hoga ..so 
    just remove it completely from the stack.

    also
    keep inserting current index on stack
    


----------------------------------------------------------   
why `i + 1` when the stack is empty?**

---

## What does `st.isEmpty()` actually mean?

It means:

> 👉 There is **NO previous day** with a price **greater than today’s price**

So today’s price is:

* Greater than or equal to **ALL previous prices**

---

## What is span by definition?

> Number of **consecutive days ending today**
> for which `price <= price[i]`

That includes:

* Today (`i`)
* All previous days (`0` to `i`)

---

## Count the days when stack is empty

Days involved:

```
Index: 0 1 2 ... i
Count: i + 1 days
```

👉 That’s where **`i + 1` comes from**

---

## Visual example (this makes it click)

### Prices

```
[10, 20, 30, 40]
```

For `i = 3` (price = 40):

* Stack becomes empty (40 beats all previous)
* Span = days `[0,1,2,3]`
* Count = 4 = i + 1


