

Tricks
 In 1-3 i need count 
 in 4th i need actual value

1
at index i ..mujhe jaan na h ..me left me kitni duur jaau aur i stricly minimum rhe

So you need Previous smaller (strict)
 
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }


2
at index i ..mujhe jaan na h me right me kitni duur jaau aur i  minimum or equal to rhe

 // Next smaller or equal
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n - i : stack.peek() - i;
            stack.push(i);
        }



3 Stock Span problem
  At index i ..mujhe jaan na h ki how many consecutive days before it (including today) had price ≤ today’s price.
i.e me left me kitni duur jaau aur i maxmum or equal to rhe


    for (int i = 0; i < n; i++) {
            // Pop while current price is higher
            while (!stack.isEmpty() && price[stack.peek()] <= price[i]) {
                stack.pop();
            }

            // If empty, no greater on left
            span[i] = stack.isEmpty() ? i + 1 : i - stack.peek();

            // Push current index
            stack.push(i);
            }


4.
 At index i , i need next smaller element ...mujhe duur jaana h but i dont need index i need value

 for (int i = 0; i < n; i++) {
    while (!st.isEmpty() && arr[i] < arr[st.peek()]) {
        nse[st.pop()] = arr[i];
    }
    st.push(i);
}
while (!st.isEmpty()){ 
 nse[st.pop()] = -1; 
}
