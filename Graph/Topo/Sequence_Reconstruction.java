Problem Description
You are given an integer array nums of length n that is a permutation of integers from 1 to n. You also have a 2D array sequences where each sequences[i] is a subsequence of nums.

Your task is to determine if nums is both:

The shortest possible supersequence that contains all given subsequences
The only unique shortest supersequence
A supersequence is a sequence that contains all the given subsequences as its subsequences. A subsequence is derived from a sequence by deleting some or no elements without changing the order of remaining elements.

For example:

Given sequences = [[1,2],[1,3]], there are two possible shortest supersequences: [1,2,3] and [1,3,2]. Since there are multiple valid shortest supersequences, the answer would be false.
Given sequences = [[1,2],[1,3],[1,2,3]], the only shortest supersequence is [1,2,3]. While [1,2,3,4] is also a valid supersequence, it's not the shortest. Since there's only one shortest supersequence, the answer would be true.
The solution uses topological sorting to solve this problem. For each consecutive pair of elements (a, b) in every subsequence, we create a directed edge a → b in a graph. We then perform topological sorting using a queue-based approach:

Build a directed graph from the subsequences and calculate in-degrees for each node
Add all nodes with in-degree 0 to a queue
Process nodes one at a time when the queue has exactly one element (ensuring unique ordering)
For each processed node, reduce the in-degree of its neighbors and add any that reach 0 to the queue
If at any point the queue has more than one element, multiple orderings are possible
Return true if the queue remains empty after processing (unique shortest supersequence exists), false otherwise
