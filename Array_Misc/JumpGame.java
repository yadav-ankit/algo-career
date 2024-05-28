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
