Design a Hit Counter that records website/API hits.

Support:

hit(timestamp)
getHits(timestamp)

where timestamp is in seconds.

What does hit(timestamp) do?

A request arrives.

hit(1);

means:

One hit happened at second 1
hit(1);
hit(1);
hit(1);

means:

3 hits happened during second 1
What does getHits(timestamp) do?

Return total hits in the last 5 minutes (300 seconds).

Example
hit(1);
hit(2);
hit(3);

Timeline:

1 -> hit
2 -> hit
3 -> hit

Now:

getHits(4);

Window:

(4 - 300, 4]
=
(-296, 4]

Contains:

1,2,3

Answer:

3
Another Example
hit(1);
hit(2);
hit(3);
hit(300);

Now:

getHits(300);

Window:

(0,300]

Contains:

1,2,3,300

Answer:

4

Now:

getHits(301);

Window:

(1,301]

The hit at timestamp 1 is no longer in the last 300 seconds.

Remaining:

2,3,300

Answer:

3
  

class HitCounter {

    private final int[] timestamps;
    private final int[] hits;

    public HitCounter() {
        timestamps = new int[300];
        hits = new int[300];
    }

    public void hit(int timestamp) {
        int idx = timestamp % 300;

        if (timestamps[idx] == timestamp) {
            hits[idx]++;
        } else {
            timestamps[idx] = timestamp;
            hits[idx] = 1;
        }
    }

    public int getHits(int timestamp) {
        int total = 0;

        for (int i = 0; i < 300; i++) {
            if (timestamp - timestamps[i] < 300) {
                total += hits[i];
            }
        }

        return total;
    }
}


ut in this case..isnt timestamp array will hold wrong values  ? suppose 2 diff timestamp hits comes but we are using mod ..we have lost actual timestamp

Excellent observation. The answer is: we don't lose the timestamp because we store it separately in timestamps[].

Let's walk through it.

Suppose:

int[] timestamps = new int[300];
int[] hits = new int[300];

Initially all 0.

hit(1)
idx = 1 % 300 = 1;

Store:

timestamps[1] = 1
hits[1] = 1
hit(301)
idx = 301 % 300 = 1

Same slot!

At first glance, it looks like we'll overwrite timestamp 1.

But that's intentional.

Before updating:

timestamps[1] = 1
hits[1] = 1

Check:

if (timestamps[idx] == timestamp)

becomes:

if (1 == 301)

False.

Therefore:

timestamps[1] = 301;
hits[1] = 1;

Now slot 1 represents second 301, not second 1.

Why is it safe to overwrite?

Because when timestamp reaches 301:

Current window = (1, 301]

The hit at timestamp 1 is already expired.

We no longer care about it.
