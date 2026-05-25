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
    
/*
Mota map

keep 2 pointers i , j 
Also we will use a map Data structure to check freuency move j to right and keep checking these 2 things 
if our map size is = window (j-i+1) ..--> keep updating max 
else if map size < (j-i+1) this means map me duplicates h so increment i and remove duplicates 
    */
    
 public int lengthOfLongestSubstring(String s) {
    Map<Character, Integer> map = new HashMap<>();
    int i = 0, max = 0;

    for(int j = 0; j < s.length(); j++) {
        map.put(s.charAt(j), map.getOrDefault(s.charAt(j), 0) + 1);

        while(map.get(s.charAt(j)) > 1) {
            map.put(s.charAt(i), map.get(s.charAt(i)) - 1);
            if(map.get(s.charAt(i)) == 0) map.remove(s.charAt(i));
            i++;
        }

        max = Math.max(max, j - i + 1);
    }

    return max;
}
