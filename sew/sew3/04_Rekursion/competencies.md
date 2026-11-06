# Competencies: Recursion

## Understanding Recursion
- Explain recursion as solving a problem by calling the same method with a smaller version of the problem.
- Distinguish direct recursion from mutual recursion.
- Explain the roles of the termination condition, base case, and recursive case.
- Decide whether a problem is suitable for recursion by identifying a directly solvable smallest case and a way to reduce other cases to smaller problems of the same kind.
- Compare recursion and iteration, including how the same task can be expressed using either approach.

## Writing and Tracing Recursive Solutions
- Develop a recursive solution step by step by defining its result, base case, and recursive case.
- Write and trace a simple recursive method, such as calculating the sum of the integers from 1 to `n`.
- Trace recursive calls to the base case and follow the return steps back through the call stack.
- Use a debugger's call stack and variable view to inspect recursive method calls.
- Identify a recursive call that makes no progress, explain why it may cause a `StackOverflowError`, and check whether each call moves toward a reachable base case.
- Explain mutual recursion using methods that process folders and their entries.

## Applying Recursion
- Explain why tree structures, such as file systems, HTML DOMs, JSON/XML data, and syntax trees, can be processed recursively.
- Trace a tree traversal and describe how recursive calls process its subtrees.
- Explain how to combine results from subtrees (for example, to calculate a tree's height).
- Explain and trace recursive binary search in a sorted list, including how each call reduces the search range.
- Explain backtracking using a maze: try directions recursively, return from a dead end, and continue with another direction.
- Explain why visited positions prevent repeatedly exploring cycles, and distinguish visited positions from positions on the successful path.
