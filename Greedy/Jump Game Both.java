https://leetcode.com/problems/jump-game/description/ 

class Solution { 
    public boolean canJump(int[] arr) { 
       int reachable = 0; 
       for(int i = 0; i < arr.length; i ++) { 
           if(i > reachable) return false; 
           reachable = Math.max(reachable, i + arr[i]); 
       }  
       return true; 
    } 
} 


-----------------------------------------------------
  https://leetcode.com/problems/jump-game-ii/description/ 

public class MinimumJumps { 

    public static int minJumps(int[] arr) { 
        if (arr.length <= 1) { 
            return 0; // If array has one element, no jumps needed 
        } 
        if (arr[0] == 0) { 
            return -1; // If the first element is 0, end cannot be reached 
        } 
        int jumps = 1; // Initial jump from the first element 
        int farthest = arr[0]; 
        int current_end = arr[0]; 

  
/* Loop through each element of the array. 
Update the farthest point that can be reached from the current position. 
If you reach the end of the current jump range (current_end), increase the jump count and update current_end to farthest. 
If current_end reaches or exceeds the last index, return the count of jumps. 
*/
 
        for (int i = 1; i < arr.length; i++) { 
            if (i == arr.length - 1) { 
                return jumps; // If we reach the end 
            } 
  
            farthest = Math.max(farthest, i + arr[i]); // Update farthest point 

            if (i == current_end) { // End of the range for the current jump 
                jumps++; 
                current_end = farthest; 

                if (current_end > arr.length) { 
                    return jumps; // If we can reach the end 
                } 
            } 
        }
        return -1; // If the end cannot be reached 
    } 
