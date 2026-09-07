# A* Maze Solver

Finds the shortest path through a grid maze using A* search, with a comparison
against uninformed search strategies.

## Running it

```bash
javac [YourMainClass].java
java [YourMainClass] [maze-file]
```

[Verify the actual class name and whether it takes a file argument.]

## Why A*

A* orders its search by `f(n) = g(n) + h(n)`:

- `g(n)` — the exact cost to reach node `n` from the start
- `h(n)` — a Manhattan-distance estimate of the remaining cost to the goal

Because the Manhattan heuristic never overestimates the true remaining distance
on a 4-directional grid, it is *admissible*, which means A* is guaranteed to
find an optimal path. The heuristic lets it skip large regions of the maze that
BFS would explore anyway.

## Implementation notes

- Maze modeled as a 2D grid encoding walls, open cells, start, and goal
- Priority queue ordered by `f(n)` selects the next node to expand
- Predecessor map reconstructs the path once the goal is reached
- Handles dead ends and unreachable goals with explicit failure output

## Benchmarks

Compared against BFS and DFS across increasing maze sizes. A* explored
measurably fewer nodes on larger mazes, with the gap widening as the search
space grew.

[Drop in your actual numbers here if you still have them — a small table of
maze size vs. nodes explored per algorithm is the single most convincing thing
you can put in this README.]
