
Given a dictionary of root words and a sentence,
replace each word in the sentence with the shortest root that is its prefix.
If no root exists, keep the word as-is.



Dictionary:

["cat", "bat", "rat"]


Sentence:

"the cattle was rattled by the battery"


Replacement:

the cat was rat by the bat


class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isWord;
}


TrieNode root = new TrieNode();

void insert(String word) {
    TrieNode node = root;
    for (char c : word.toCharArray()) {
        int idx = c - 'a';
        if (node.children[idx] == null)
            node.children[idx] = new TrieNode();
        node = node.children[idx];
    }
    node.isWord = true;
}


public String replaceWords(List<String> dictionary, String sentence) {
    for (String word : dictionary) {
        insert(word);
    }

    StringBuilder result = new StringBuilder();

    for (String word : sentence.split(" ")) {
        TrieNode node = root;
        StringBuilder prefix = new StringBuilder();
        boolean replaced = false;

        for (char c : word.toCharArray()) {
            int idx = c - 'a';

            if (node.children[idx] == null) break;

            node = node.children[idx];
            prefix.append(c);

            if (node.isWord) {
                result.append(prefix).append(" ");
                replaced = true;
                break;
            }
        }

        if (!replaced) {
            result.append(word).append(" ");
        }
    }

    return result.toString().trim();
}

🌳 Key Insight (THIS IS THE WHOLE TRICK)

As soon as we find a valid root in the Trie, we stop.

We don’t look for longer prefixes — shortest root wins.
