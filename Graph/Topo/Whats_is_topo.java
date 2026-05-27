

What is Topological Sort?

Suppose:

Wake up → Brush → Breakfast → Office

You cannot do:

Office → Brush → Breakfast

because dependencies exist.

Graph:

Wake up → Brush → Breakfast → Office

Topological order:

[Wake up, Brush, Breakfast, Office]

Meaning:

An ordering of nodes such that for every edge:

u → v

u comes before v.

  Example:

Courses:

0 → 1
0 → 2
1 → 3
2 → 3

Meaning:

Take 0 before 1
Take 0 before 2
Take 1 before 3
Take 2 before 3

Valid topo orders:

0 1 2 3
0 2 1 3


  What is Kahn's Algorithm?

Kahn’s Algorithm = BFS approach to Topological Sort

Idea:

Take nodes with:

indegree = 0

Meaning:

No prerequisites

Do them first.

Example:

Graph:

0 → 1
0 → 2
1 → 3
2 → 3

Adjacency:

adj[0] = [1,2];
adj[1] = [3];
adj[2] = [3];

Indegree:

0 : 0
1 : 1
2 : 1
3 : 2

Meaning:

0 depends on nobody
1 depends on 0
2 depends on 0
3 depends on 1,2


Kahn's Algorithm Steps (memorize)
Step 1: Build graph
adj[prerequisite].add(course);
Step 2: Compute indegree
indegree[course]++;
Step 3: Add indegree=0 nodes
if(indegree[i] == 0)
    queue.offer(i);
Step 4: BFS

Pop:

int node = queue.poll();

Add to answer:

answer.add(node);

Reduce neighbors:

indegree[neighbor]--;

Push:

if(indegree[neighbor] == 0)
    queue.offer(neighbor);
Template (memorize)
Queue<Integer> queue = new LinkedList<>();

for(int i = 0; i < n; i++) {

    if(indegree[i] == 0)
        queue.offer(i);
}


while(!queue.isEmpty()) {

    int node = queue.poll();

    for(int neighbor : adj[node]) {

        indegree[neighbor]--;

        if(indegree[neighbor] == 0)
            queue.offer(neighbor);
    }
}


How detect cycle?

Suppose:

0 → 1
1 → 0

Indegree:

0:1
1:1

Queue:

[]

Nothing to process.

Processed:

0 nodes

Total:

2 nodes

Since:

processed != total

Cycle exists.
