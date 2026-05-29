
This is the cheat sheet I wish every backtracking learner got on Day 1.

Pattern 1: Subsets / Subsequences

Questions:

Print all subsets
Subset Sum
Subsequences
Take/Not Take type problems
Idea

For every element:

Take it
OR
Skip it
Template
void backtrack(int index){

    if(index == n){
        // process answer
        return;
    }

    // take
    path.add(arr[index]);
    backtrack(index + 1);
    path.remove(path.size() - 1);

    // skip
    backtrack(index + 1);
}
Tree
1
├── take
└── skip
Number of choices
2 choices per element
Pattern 2: Combinations

Questions:

nCk
Combination Sum II
Choose k elements
Generate combinations
Idea

Choose next element from remaining candidates.

Template
void backtrack(int start){

    if(answer formed){
        return;
    }

    for(int i = start; i < n; i++){

        path.add(nums[i]);

        backtrack(i + 1);

        path.remove(path.size() - 1);
    }
}
Tree
[]
├── 1
│   ├── 2
│   ├── 3
│   └── 4
├── 2
├── 3
└── 4
Why i + 1?
Never revisit previous elements.
Order doesn't matter.
Number of choices
Many choices at each level

  
Pattern 3: Permutations

Questions:

Permutations
Arrange elements
Ordering matters
Idea

Choose any unused element.

Template
void backtrack(){

    if(path.size() == n){
        ans.add(new ArrayList<>(path));
        return;
    }

    for(int i = 0; i < n; i++){

        if(used[i]){
            continue;
        }

        used[i] = true;
        path.add(nums[i]);

        backtrack();

        path.remove(path.size() - 1);
        used[i] = false;
    }
}
Tree
[]
├── 1
├── 2
└── 3

At next level:

[1]
├── 2
└── 3
Why used[]?

Because:

[1,2]
and
[2,1]

are different answers.

Order matters.
