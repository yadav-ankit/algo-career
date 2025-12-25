class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // INSERT a word into the trie
    public void insert(String word) {
        TrieNode node = root;

        for (char c : word.toCharArray()) {
            int idx = c - 'a';

            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }

            node = node.children[idx];
        }

        node.isWord = true;
    }

    // SEARCH exact word
    public boolean search(String word) {
        TrieNode node = root;

        for (char c : word.toCharArray()) {
            int idx = c - 'a';

            if (node.children[idx] == null) {
                return false;
            }

            node = node.children[idx];
        }

        return node.isWord;
    }

    // PREFIX search (optional but important)
    public boolean startsWith(String prefix) {
        TrieNode node = root;

        for (char c : prefix.toCharArray()) {
            int idx = c - 'a';

            if (node.children[idx] == null) {
                return false;
            }

            node = node.children[idx];
        }

        return true;
    }
}

🧠 How to Visualize This (Quick Mental Model)

Insert

Walk character by character

Create node if missing

Mark isWord = true at the end

Search

Walk character by character

If any node missing → false

At end → check isWord

👉 Navigation, not scanning — that’s why Trie is fast.

⏱️ Complexity (Interview Safe)
Operation	Time
Insert	O(L)
Search	O(L)
Prefix	O(L)

Where L = length of the word
