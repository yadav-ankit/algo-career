

// Background cleaner
    private final ScheduledExecutorService cleaner = Executors.newSingleThreadScheduledExecutor();


cleaner.scheduleAtFixedRate(this::evictExpired, ttlMillis / 2, ttlMillis / 2, TimeUnit.MILLISECONDS);


----------------


    The Object
java
class Student {
    String name;
    int age;
    double gpa;

    Student(String name, int age, double gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return name + "(age=" + age + ", gpa=" + gpa + ")";
    }
}
2. Min Heap — 3 Ways
Way 1 — Lambda (most common)
java
// Min heap by age (smallest age = highest priority)
PriorityQueue<Student> minHeap = new PriorityQueue<>(
    (a, b) -> a.age - b.age
);
Way 2 — Comparator.comparingInt
java
PriorityQueue<Student> minHeap = new PriorityQueue<>(
    Comparator.comparingInt(s -> s.age)
);
Way 3 — Implement Comparable in class
java
class Student implements Comparable<Student> {
    // ... fields ...

    @Override
    public int compareTo(Student other) {
        return this.age - other.age; // min heap by age
    }
}

PriorityQueue<Student> minHeap = new PriorityQueue<>(); // uses compareTo
3. Max Heap — 3 Ways
Way 1 — Lambda reversed
java
// Max heap by age (largest age = highest priority)
PriorityQueue<Student> maxHeap = new PriorityQueue<>(
    (a, b) -> b.age - a.age
);
Way 2 — Comparator.comparingInt reversed
java
PriorityQueue<Student> maxHeap = new PriorityQueue<>(
    Comparator.comparingInt((Student s) -> s.age).reversed()
);
Way 3 — Collections.reverseOrder (only if Comparable implemented)
java
PriorityQueue<Student> maxHeap = new PriorityQueue<>(
    Collections.reverseOrder()
);


