import java.util.*;

/*
PRINT ALL SEQUENCES

we can still use BFS, but we need to keep track of the paths as we explore them. Instead of just counting the steps, we will store the entire path leading to each string.

Here's how we can modify the previous implementation to achieve this:

Use BFS to explore all possible transformations.
Keep track of the paths leading to each string.
When we reach the target string, store the current path.
We'll maintain a queue of pairs where each pair consists of a string and the path taken to reach that string.
*/
public class StringTransformation {
    public static List<List<String>> allSequencesToTarget(String start, String target, List<String> str) {
        Set<String> wordSet = new HashSet<>(str);
        List<List<String>> results = new ArrayList<>();
        
        if (!wordSet.contains(target)) {
            return results; // Target is not in the word list
        }
        
        if (start.equals(target)) {
            results.add(Collections.singletonList(start));
            return results;
        }

        Queue<List<String>> queue = new LinkedList<>();
        queue.add(Arrays.asList(start));
        Set<String> visited = new HashSet<>();
        visited.add(start);
        boolean found = false;

        while (!queue.isEmpty() && !found) {
            int size = queue.size();
            Set<String> currentLevelVisited = new HashSet<>();
            
            for (int i = 0; i < size; i++) {
                List<String> path = queue.poll();
                String current = path.get(path.size() - 1);
                
                for (String next : getNextStates(current, wordSet)) {
                    if (!visited.contains(next)) {
                        List<String> newPath = new ArrayList<>(path);
                        newPath.add(next);
                        if (next.equals(target)) {
                            results.add(newPath);
                            found = true;
                        } else {
                            queue.add(newPath);
                            currentLevelVisited.add(next);
                        }
                    }
                }
            }
            visited.addAll(currentLevelVisited);
        }

        return results;
    }

    private static List<String> getNextStates(String word, Set<String> wordSet) {
        List<String> nextStates = new ArrayList<>();
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char originalChar = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == originalChar) {
                    continue;
                }
                chars[i] = c;
                String newWord = new String(chars);
                if (wordSet.contains(newWord)) {
                    nextStates.add(newWord);
                }
            }
            chars[i] = originalChar;
        }
        return nextStates;
    }

    public static void main(String[] args) {
        String start = "hit";
        String target = "cog";
        List<String> str = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");

        List<List<String>> result = allSequencesToTarget(start, target, str);
        for (List<String> path : result) {
            System.out.println(path);
        }
    }
}
