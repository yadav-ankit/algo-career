https://www.geeksforgeeks.org/dsa/2-sum-pair-sum-closest-to-target/#expected-approach-using-two-pointer-technique-onlognn-time-and-o1-space

function sumClosest(arr, target) {
    let n = arr.length;
    arr.sort((a, b) => a - b);
    let res = [];
    let minDiff = Number.MAX_VALUE;

    let left = 0, right = n - 1;

    while (left < right) {
        let currSum = arr[left] + arr[right];

        // check if this pair is closer than the closest
        // pair so far
        if (Math.abs(target - currSum) < minDiff) {
            minDiff = Math.abs(target - currSum);
            res = [arr[left], arr[right]];
        }

        // if this pair has less sum, move to greater values
        if (currSum < target) 
            left++;
        
        // if this pair has more sum, move to smaller values
        else if (currSum > target) 
            right--;
        
        // if this pair has sum = target, return it
        else 
            return res;
    }

    return res;
}

// Driver Code
let arr = [5, 2, 7, 1, 4];
let target = 10;

let res = sumClosest(arr, target);
if (res.length > 0) 
    console.log(res[0] + " " + res[1]);
