https://www.youtube.com/watch?v=-zSxTJkcdAo

// simpl
l = 0; r = 0; 
maxLen = 0; 
while (r < n) 
{ 
    // In the map 
    if (hash[s[r]] != -1) 
    { 
        if (hash[s[r]] >= l) 
        { 
            l = hash[s[r]] + 1; 
        } 
    } 

    len = r - l + 1; 
    maxLen = max(len, maxLen); 
    hash[s[r]] = r; 
    r++; 

} 

--------------------------

  //Mota map
//keep 2 pointers i , j 

//Also we will use a map Data structure to check freuency move j to right and keep checking these 2 things 

//if our map size is = window (j-i+1) ..--> keep updating max 

else if map size < (j-i+1) this means map me duplicates h so increment i and remove duplicates 
  class Solution { 
    public int lengthOfLongestSubstring(String s) { 
        Map<Character, Integer> map = new HashMap<>(); 
        int i = 0; 
        int j = 0; 
        int max = 0; 
      
        while(j < s.length()){ 
            map.put(s.charAt(j), map.getOrDefault(s.charAt(j), 0) + 1);
          
            if(map.size() == j - i + 1){ 
                max = Math.max(max, j - i + 1); 
                j++; 
            } 
            else if(map.size() < j - i + 1){ 
                while(map.size() < j - i + 1){ 
                    map.put(s.charAt(i), map.get(s.charAt(i)) - 1); 
                    if(map.get(s.charAt(i)) == 0) map.remove(s.charAt(i)); 
                    i++; 
                } 
                j++; 
            } 
        } 
        return max; 
    } 
} 
