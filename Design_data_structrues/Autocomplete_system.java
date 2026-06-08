Design Autocomplete System Operations: insert word search prefix top k suggestions

  | Operation              | Complexity     |
| ---------------------- | -------------- |
| insert(word)           | O(W)           |
| search(prefix)         | O(L + M)       |
| topK (full heap)       | O(L + M log M) |
| topK (min heap size K) | O(L + M log K) |

  Assume:

L = prefix length
M = number of words matching the prefix
  
  
Trie
 ↓
Find prefix node

DFS
 ↓
Collect all matching words

Heap
 ↓
Rank by frequency

Poll K times
 ↓
Top K suggestions


Great question. Let's slow down and understand why we need both DFS and Heap.

Suppose the Trie contains:

app         -> 100
apple       -> 80
application -> 30
apply       -> 20
ape         -> 50

And user asks:

topK("app", 2)

Expected:

app
apple

because they have highest frequencies.

Step 1: Find the Prefix Node

Traverse:

root
 |
 a
 |
 p
 |
 p

Now we are at the Trie node representing:

"app"
What does this node know?

Only:

From here, all words start with "app"

It does NOT automatically know:

app -> 100
apple -> 80
application -> 30
apply -> 20

Those words are scattered throughout the subtree.

Step 2: DFS

Starting from node "app":

Explore every path below it.

app
├── le
│   └── apple
├── lication
│   └── application
└── ly
    └── apply

DFS collects:

app         100
apple       80
application 30
apply       20

Think of DFS as:

"Give me all words that start with this prefix."

After DFS, we have:

[
 (app,100),
 (apple,80),
 (application,30),
 (apply,20)
]
Why Not Return This Directly?

Because question asks:

topK("app", 2)

Need:

Highest frequency first

DFS order is arbitrary.

Could be:

application
apple
apply
app

depending on traversal.

Not useful.

Step 3: Heap

Now take DFS results and put them into a Max Heap.

Heap ordered by frequency:

            app(100)
           /        \
     apple(80)   application(30)
         /
    apply(20)

Top element always has highest frequency.

Now:

heap.poll()

returns:

app

Next:

heap.poll()

returns:

apple

Done.

Top 2 suggestions found.

Analogy


Imagine searching a folder.

DFS

Find all matching files:

app.txt
apple.txt
application.txt
apply.txt
Heap

Sort them by popularity:

app         100
apple        80
application  30
apply        20

Return first K.



how are we storing frequency also in trie?

Excellent question.

A Trie node normally stores only:

class TrieNode {
    Map<Character, TrieNode> children;
    boolean isWord;
}

This tells us:

Does a word end here?
What are the next characters?

But for autocomplete we also need:

How popular is this word?

So we store frequency at the terminal node of the word.

Example

Insert:

app         -> 100
apple       -> 80
application -> 30

Trie:

root
 |
 a
 |
 p
 |
 p (isWord=true, freq=100)
 |
 l
 |
 e (isWord=true, freq=80)
 |
 ...
 |
 n (isWord=true, freq=30)

Notice:

app

ends at the 2nd p.

So frequency 100 is stored there.

Trie Node
class TrieNode {

    Map<Character, TrieNode> children = new HashMap<>();

    boolean isWord;

    int frequency;
}
Insert "app"

Walk:

a -> p -> p

At final node:

node.isWord = true;
node.frequency = 100;
Insert "apple"

Walk:

a -> p -> p -> l -> e

At node e:

node.isWord = true;
node.frequency = 80;
During DFS

When DFS reaches a node:

if (node.isWord)

we know:

Current string = a valid word

and:

node.frequency

tells us its popularity.
