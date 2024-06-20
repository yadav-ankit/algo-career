import java.util.*;
// print min no. of steps requred to reach target

public class StringTransformation {
    public static int minStepsToTarget(String start, String target, List<String> str) {
        Set<String> wordSet = new HashSet<>(str);
        if (!wordSet.contains(target)) {
            return -1;
        }
        if (start.equals(target)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        Set<String> visited = new HashSet<>();
        visited.add(start);
        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            steps++;
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                for (String next : getNextStates(current, wordSet)) {
                    if (next.equals(target)) {
                        return steps;
                    }
                    if (!visited.contains(next)) {
                        visited.add(next);
                        queue.add(next);
                    }
                }
            }
        }

        return -1; // Target not reachable
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

        int result = minStepsToTarget(start, target, str);
        System.out.println(result); // Output should be 5
    }
}
