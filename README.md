# A* Maze Solver

A Java implementation of A* search that finds the shortest path through a maze
modeled as an undirected graph.

## Running it

```bash
javac *.java
java Maze3
```

The maze is hardcoded in `Maze3.buildGraph()`; the program takes no arguments.

Output:

```
Starting point: 03
Goal point: 30
Path: 03 -> 13 -> 23 -> 22 -> 12 -> 02 -> 01 -> 11 -> 21 -> 31 -> 30
```

Cells print as `colRow` (see `State.toString`), so `03` is column 0, row 3 (the
bottom-left start) and `30` is column 3, row 0 (the top-right goal).

## Files

| File | Purpose |
|------|---------|
| `Maze3.java` | Builds the maze graph, runs A*, rebuilds and prints the path |
| `AdjacencyList.java` | Undirected graph backed by a `HashMap<Integer, ArrayList<Integer>>` |
| `State.java` | A (row, col) cell position |

## How it works

A* expands nodes in order of `f(n) = g(n) + h(n)`:

- `g(n)`: the exact number of steps taken from the start to `n`
- `h(n)`: the Manhattan distance from `n` to the goal

On a 4-directional grid with unit step cost, Manhattan distance never
overestimates the true remaining distance, so the heuristic is *admissible* and
the first time the goal is popped from the queue its path is optimal.

## Implementation notes

- **Graph representation:** the maze is an adjacency list of open passages. Each
  cell `(r, c)` maps to a single integer ID (`r * cols + c`). Walls are
  represented by the absence of an edge, so only open connections are added in
  `buildGraph()`.
- **Priority queue:** a `PriorityQueue<PQItem>` ordered by `f`, with ties broken
  by node ID so results are deterministic.
- **No decrease-key:** when a cheaper route to a node is found, a new queue entry
  is added and stale entries are skipped when popped (the `visited` check).
- **Path reconstruction:** a `cameFrom` array records each node's predecessor,
  and the path is rebuilt by walking back from the goal and reversing.
- **Unreachable goal:** if the queue empties without reaching the goal, `aStar`
  returns `null` and the program prints `No path found.`

## Limitations

- The maze is a hardcoded 4x4 grid (`rows` and `cols` constants in `Maze3`); it
  is not read from a file.
- Only A* is implemented. There is no BFS/DFS comparison or benchmarking yet.
