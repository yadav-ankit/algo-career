
What is a Huffman Tree?

A Huffman Tree is a binary tree used for data compression.

Idea:

Characters occurring more frequently → get shorter binary codes
Characters occurring less frequently → get longer binary codes

This reduces total bits needed.

Example:

String:

AAABBCCCCD

Frequency:

A → 3
B → 2
C → 4
D → 1

Build tree by combining lowest frequencies:

D(1) + B(2) → 3
A(3) + DB(3) → 6
C(4) + ADB(6) → 10

Tree:

          (10)
         /    \
      C(4)    (6)
             /   \
          A(3)   (3)
                /   \
             D(1)   B(2)

Assign:

left = 0
right = 1

Codes:

C → 0
A → 10
D → 110
B → 111

Encoded:

AAABBCCCCD
↓

10 10 10 111 111 0 0 0 0 110
  

To construct a Huffman Tree:

Count frequency of each character
Put all chars into min heap ordered by frequency
Remove two smallest nodes
Create parent:
parent.freq = left.freq + right.freq
Insert parent back into heap
Repeat until one node remains → root

Java code:

import java.util.*;

class Node {
    char ch;
    int freq;
    Node left;
    Node right;

    Node(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }

    Node(char ch, int freq, Node left, Node right) {
        this.ch = ch;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }
}

public class HuffmanTree {

    public static Node buildHuffmanTree(String s) {

        // frequency map
        Map<Character, Integer> freqMap = new HashMap<>();

        for(char c : s.toCharArray())
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);

        // min heap by frequency
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.freq - b.freq);

        for(Map.Entry<Character, Integer> entry : freqMap.entrySet())
            pq.offer(new Node(entry.getKey(), entry.getValue()));

        // build tree
        while(pq.size() > 1) {

            Node left = pq.poll();
            Node right = pq.poll();

            Node parent = new Node('\0', left.freq + right.freq, left, right);

            pq.offer(parent);
        }

        return pq.poll(); // root
    }

    public static void printCodes(Node root, String code) {

        if(root == null)
            return;

        if(root.left == null && root.right == null)
            System.out.println(root.ch + " -> " + code);

        printCodes(root.left, code + "0");
        printCodes(root.right, code + "1");
    }

    public static void main(String[] args) {

        String s = "AAABBCCCCD";

        Node root = buildHuffmanTree(s);

        printCodes(root, "");
    }
}

Input:

AAABBCCCCD

Frequency:

A → 3
B → 2
C → 4
D → 1

Possible output:

C -> 0
A -> 10
D -> 110
B -> 111

(Huffman codes can vary depending on tie-breaking.)

Tree looks like:

          (10)
         /    \
      C(4)    (6)
             /   \
          A(3)   (3)
                /   \
             D(1)   B(2)

Internal nodes:

ch = '\0'
freq = left.freq + right.freq

Leaf nodes:

ch = actual character
freq = character frequency

Complexity:

Frequency map:
O(n)
Heap operations:
O(k log k)

where:

k = unique characters

Total:

O(n + k log k)

This is the standard Huffman Tree construction asked in interviews.
