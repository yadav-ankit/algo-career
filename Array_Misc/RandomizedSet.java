import java.util.*;


mplement the RandomizedSet class:

RandomizedSet() Initializes the RandomizedSet object.
bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.
You must implement the functions of the class such that each function works in average O(1) time complexity.


✅ Core Idea

Use two data structures together:

Dynamic array (vector / list) → store elements

Hash map (value → index in array) → track positions

This combo allows:

Operation	Why O(1)?
Insert	push_back + hashmap insert
Remove	swap with last element + pop_back
getRandom	random index look

class RandomizedSet {

    private ArrayList<Integer> nums;
    private HashMap<Integer, Integer> map;
    private Random rand;

    public RandomizedSet() {
        nums = new ArrayList<>();
        map = new HashMap<>();
        rand = new Random();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }

        nums.add(val);
        map.put(val, nums.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }

        int index = map.get(val);
        int lastElement = nums.get(nums.size() - 1);

        // Move last element to the index of element to remove
        nums.set(index, lastElement);
        map.put(lastElement, index);

        // Remove last
        nums.remove(nums.size() - 1);
        map.remove(val);

        return true;
    }

    public int getRandom() {
        int randomIndex = rand.nextInt(nums.size());
        return nums.get(randomIndex);
    }
}
