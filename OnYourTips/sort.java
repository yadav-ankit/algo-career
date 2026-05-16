1. Sort Map<Integer, Integer> by key

Example:

Map<Integer, Integer> map = new HashMap<>();
map.put(3, 30);
map.put(1, 10);
map.put(2, 20);

Map<Integer, Integer> sorted =
        new TreeMap<>(map);

System.out.println(sorted);

Output:

{1=10, 2=20, 3=30}

Complexity:

O(n log n)
2. Sort Map<Integer, Integer> by value

Need stream + comparator:

Map<Integer, Integer> map = new HashMap<>();

LinkedHashMap<Integer, Integer> sorted =
        map.entrySet()
           .stream()
           .sorted(Map.Entry.comparingByValue())
           .collect(Collectors.toMap(
                   Map.Entry::getKey,
                   Map.Entry::getValue,
                   (a,b)->a,
                   LinkedHashMap::new
           ));

Example:

{1=50,2=10,3=20}

becomes:

{2=10,3=20,1=50}
Now: Map<Integer, List<Integer>>

Example:

Map<Integer, List<Integer>> map = new HashMap<>();

map.put(3, Arrays.asList(5,1));
map.put(1, Arrays.asList(9));
map.put(2, Arrays.asList(7,8,6));

Need to decide sorting criterion.

3. Sort by key
Map<Integer, List<Integer>> sorted =
        new TreeMap<>(map);

Output:

1 -> [9]
2 -> [7,8,6]
3 -> [5,1]
4. Sort by list size

Suppose:

1 -> [9]
2 -> [7,8,6]
3 -> [5,1]

Sort by:

size(list)

Code:

LinkedHashMap<Integer, List<Integer>> sorted =
        map.entrySet()
           .stream()
           .sorted(
               Comparator.comparingInt(
                   e -> e.getValue().size()
               )
           )
           .collect(Collectors.toMap(
                   Map.Entry::getKey,
                   Map.Entry::getValue,
                   (a,b)->a,
                   LinkedHashMap::new
           ));

Result:

1 -> [9]
3 -> [5,1]
2 -> [7,8,6]
5. Sort by first element in list

Example:

3 -> [5,1]
1 -> [9]
2 -> [7,8,6]

Sort by:

list.get(0)

Code:

.sorted(
    Comparator.comparingInt(
        e -> e.getValue().get(0)
    )
)

Result:

3 -> [5,1]
2 -> [7,8,6]
1 -> [9]
6. Sort by sum of list values

Example:

3 -> [5,1]     sum=6
1 -> [9]       sum=9
2 -> [7,8,6]   sum=21

Code:

.sorted(
    Comparator.comparingInt(
        e -> e.getValue()
              .stream()
              .mapToInt(Integer::intValue)
              .sum()
    )
)

Output:

3 -> [5,1]
1 -> [9]
2 -> [7,8,6]
