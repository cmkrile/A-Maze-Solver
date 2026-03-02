# A-Maze-Solver
● Implemented the A* search algorithm to compute optimal paths through grid-based mazes, balancing path cost and
heuristic estimates.

● Modeled the maze as a 2D grid/adjacency matrix encoding walls, open cells, start, and goal to support fast neighbor
lookup.

● Used a priority queue ordered by f(n) = g(n) + h(n), tracking exact path cost and a Manhattan-distance heuristic to
prioritize the most promising nodes and reduce unnecessary exploration.

● Tracked predecessors for each visited cell to reconstruct the shortest path and handled dead ends and unreachable
goals with clear success/failure output.

●Analyzed runtime behavior and compared A* against uninformed search strategies, showing significantly fewer
explored nodes on larger mazes.
