Here's a **high-level QuadTree skeleton** that demonstrates the core CRUD operations without getting bogged down in edge cases.

  A QuadTree is a tree data structure used to efficiently store and search points in a 2-dimensional space.
The main idea is simple:
If an area contains too many points, divide it into 4 smaller regions and distribute the points among them.
  
Basic Idea
Suppose you have a map:
+-------------------+
|                   |
|    A      B       |
|                   |
|         C         |
|                   |
| D             E   |
+-------------------+
If we allow only 1 point per region, then this region becomes crowded.
We split it into 4 equal parts:
+---------+---------+
|    A    |    B    |
|         |         |
+---------+---------+
|    D    | C   E   |
|         |         |
+---------+---------+
These are:
NW | NE
-------
SW | SE
Each quadrant can further split if it becomes crowded.
Recursive Division
Imagine these points:
(1,1)
(2,2)
(3,3)
(4,4)
(5,5)
(6,6)
Root covers:
(0,0) -> (8,8)
+------------------+
|                  |
|          *       |
|       *          |
|                  |
|    *             |
| *                |
+------------------+
After splitting:
Root
├── NW
├── NE
├── SW
└── SE
Suppose all points fall into SW.
Then SW splits again:
Root
├── NW
├── NE
├── SW
│   ├── NW
│   ├── NE
│   ├── SW
│   └── SE
└── SE
This continues until each node contains only a small number of points.
  
## Data Structures

```java
class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Boundary {
    int minX, minY;
    int maxX, maxY;

    Boundary(int minX, int minY, int maxX, int maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    boolean contains(Point p) {
        return p.x >= minX &&
               p.x <= maxX &&
               p.y >= minY &&
               p.y <= maxY;
    }

    boolean intersects(Boundary other) {
        return !(other.maxX < minX ||
                 other.minX > maxX ||
                 other.maxY < minY ||
                 other.minY > maxY);
    }
}
```

---

## QuadTree Node

```java
class QuadTreeNode {

    static final int CAPACITY = 4;

    Boundary boundary;
    List<Point> points = new ArrayList<>();

    QuadTreeNode nw;
    QuadTreeNode ne;
    QuadTreeNode sw;
    QuadTreeNode se;

    boolean divided = false;

    QuadTreeNode(Boundary boundary) {
        this.boundary = boundary;
    }
}
```

---

# CREATE / INSERT

```java
boolean insert(QuadTreeNode node, Point p) {

    if (!node.boundary.contains(p))
        return false;

    if (!node.divided &&
        node.points.size() < QuadTreeNode.CAPACITY) {

        node.points.add(p);
        return true;
    }

    if (!node.divided) {
        subdivide(node);
    }

    return insert(node.nw, p)
        || insert(node.ne, p)
        || insert(node.sw, p)
        || insert(node.se, p);
}
```

---

## Split Node

```java
void subdivide(QuadTreeNode node) {

    int midX = (node.boundary.minX + node.boundary.maxX) / 2;
    int midY = (node.boundary.minY + node.boundary.maxY) / 2;

    node.nw = new QuadTreeNode(
        new Boundary(
            node.boundary.minX,
            midY,
            midX,
            node.boundary.maxY));

    node.ne = new QuadTreeNode(
        new Boundary(
            midX,
            midY,
            node.boundary.maxX,
            node.boundary.maxY));

    node.sw = new QuadTreeNode(
        new Boundary(
            node.boundary.minX,
            node.boundary.minY,
            midX,
            midY));

    node.se = new QuadTreeNode(
        new Boundary(
            midX,
            node.boundary.minY,
            node.boundary.maxX,
            midY));

    node.divided = true;

    // redistribute existing points
    List<Point> old = new ArrayList<>(node.points);
    node.points.clear();

    for (Point p : old) {
        insert(node, p);
    }
}
```

---

# READ / RANGE SEARCH

Find all points inside a rectangle.

```java
void search(
        QuadTreeNode node,
        Boundary query,
        List<Point> result) {

    if (!node.boundary.intersects(query))
        return;

    for (Point p : node.points) {
        if (query.contains(p))
            result.add(p);
    }

    if (!node.divided)
        return;

    search(node.nw, query, result);
    search(node.ne, query, result);
    search(node.sw, query, result);
    search(node.se, query, result);
}
```

Usage:

```java
List<Point> result = new ArrayList<>();

search(root,
       new Boundary(10,10,50,50),
       result);
```

---

# DELETE

High-level approach:

1. Traverse to leaf.
2. Remove point.
3. Optionally merge children later.

```java
boolean delete(QuadTreeNode node, Point target) {

    if (!node.boundary.contains(target))
        return false;

    if (!node.divided) {
        return node.points.removeIf(
            p -> p.x == target.x &&
                 p.y == target.y);
    }

    return delete(node.nw, target)
        || delete(node.ne, target)
        || delete(node.sw, target)
        || delete(node.se, target);
}
```

Production implementations often add:

```java
mergeChildrenIfSparse(node);
```

after deletion.

---

# UPDATE

Usually implemented as:

```java
boolean update(
        QuadTreeNode root,
        Point oldPoint,
        Point newPoint) {

    if (!delete(root, oldPoint))
        return false;

    return insert(root, newPoint);
}
```

---

# Example

```java
QuadTreeNode root =
    new QuadTreeNode(
        new Boundary(0, 0, 100, 100));

insert(root, new Point(10,10));
insert(root, new Point(20,20));
insert(root, new Point(30,30));
insert(root, new Point(40,40));
insert(root, new Point(60,60));
```

After the 5th insert:

```text
Root
├── NW
├── NE [60,60]
├── SW [10,20,30,40]
└── SE
```

The important thing to remember is that a QuadTree is basically:

```text
INSERT:
    Find quadrant
    Split if capacity exceeded

SEARCH:
    Visit only intersecting quadrants

DELETE:
    Find leaf
    Remove point

UPDATE:
    Delete + Insert
```

Everything else (nearest-neighbor search, balancing, merging, depth limits) is an optimization on top of these fundamentals.
