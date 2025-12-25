Given an XML structure, what data structure would you use to represent the nested tags and values? 
  Write a class to represent that. Then write a function to removeFirstMatchingNode(Node root, String tagname).

import java.util.*;

class Node {
    String tag;
    String value;              // text inside tag (optional)
    List<Node> children;

    Node(String tag) {
        this.tag = tag;
        this.children = new ArrayList<>();
    }

    Node(String tag, String value) {
        this.tag = tag;
        this.value = value;
        this.children = new ArrayList<>();
    }
}


public class XmlTreeUtils {

    public static Node removeFirstMatchingNode(Node root, String tagName) {
        if (root == null) return null;

        // If root itself matches, remove it
        if (root.tag.equals(tagName)) {
            return null;
        }

        removeHelper(root, tagName);
        return root;
    }

    private static boolean removeHelper(Node current, String tagName) {
        Iterator<Node> iterator = current.children.iterator();

        while (iterator.hasNext()) {
            Node child = iterator.next();

            // Found match → remove and stop
            if (child.tag.equals(tagName)) {
                iterator.remove();
                return true;
            }

            // Recurse into subtree
            if (removeHelper(child, tagName)) {
                return true;
            }
        }
        return false;
    }
}

7️⃣ Time & Space Complexity
Metric	Value
Time	O(n) worst case
Space	O(h) recursion stack

Where:

n = number of nodes

h = height of XML tree

  nterview One-Liner (🔥 use this)

“XML is naturally modeled as an N-ary tree. To remove the first matching tag, we do a DFS traversal
  and remove the matching child from its parent’s children list with early termination.”
