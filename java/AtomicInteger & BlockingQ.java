
AtomicInteger in Java ultimately relies on a CPU instruction called Compare-And-Swap (CAS) (or equivalent atomic primitives provided by the hardware).
At a high level:

AtomicInteger count = new AtomicInteger(5);

count.incrementAndGet();
is conceptually implemented as:
while (true) {
    int oldValue = count;
    int newValue = oldValue + 1;

    if (CAS(count, oldValue, newValue)) {
        return newValue;
    }
}
Why normal increment isn't atomic
A regular increment:
count++;
actually becomes:
1. Load count from memory
2. Add 1
3. Store back
Suppose two threads execute simultaneously:
count = 5

Thread A loads 5
Thread B loads 5

Thread A stores 6
Thread B stores 6

Final value = 6 (should be 7)
This is a race condition.
What CAS does
CAS is a single atomic CPU operation:
CAS(address, expected, newValue)
Meaning:
if (*address == expected)
    *address = newValue
    return true
else
    return false
And the entire check + update happens atomically.


  -------------------------------------------------------------------------

https://medium.com/@reetesh043/blockingqueue-in-java-36ed1ee8e9f5
