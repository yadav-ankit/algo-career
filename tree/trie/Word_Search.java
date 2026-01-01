https://leetcode.com/problems/design-add-and-search-words-data-structure/description/

// We use a Trie to store words, and 
// perform DFS during search; when encountering '.', we branch into all possible children.
  
class WordDictionary {

    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode node = root;

        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }

        node.isEnd = true;
    }

    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    private boolean dfs(String word, int index, TrieNode node) {
        if (node == null) return false;

        if (index == word.length()) {
            return node.isEnd;
        }

        char c = word.charAt(index);

        if (c == '.') {
            // try all possibilities
            for (TrieNode child : node.children) {
                if (child != null && dfs(word, index + 1, child)) {
                    return true;
                }
            }
            return false;
        } else {
            return dfs(word, index + 1, node.children[c - 'a']);
        }
    }

    // Trie Node definition
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }
}

⏱ Complexity
addWord

  Time: O(L)
  Space: O(L)

search

  Worst-case Time: O(26^L) (all dots)
  Typical: much faster
  Space: O(L) recursion stack
