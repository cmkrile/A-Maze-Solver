import java.util.*;
public class Maze3 {
    public static final int rows = 4;
    public static final int cols = 4;
    public static final AdjacencyList myList = new AdjacencyList();
    //used adjacency list based on geeksforgeeks recomendation for graph representation

    public static int id(int r, int c) {
        return r * cols + c;
    }
    public static int rowID(int id) {
        return id / cols;
    }
    public static int colID(int id) {
        return id % cols;
    }
    public static void connect(int r1, int c1, int r2, int c2) {
        myList.addEdge(id(r1, c1), id(r2, c2));
    }
    public static void buildGraph() {
        connect(3,0, 3,1);
        connect(3,1, 3,2);
        connect(3,2, 3,3);
        connect(3,2, 2,2);
        connect(3,3, 2,3);

        connect(2,0, 2,1);
        connect(2,1, 2,2);
        connect(2,0, 1,0);

        connect(1,0, 1,1);
        connect(1,1, 1,2);
        connect(1,2, 1,3);
        connect(1,0, 0,0);

        connect(0,1, 0,2);
        connect(0,2, 0,3);
        connect(1,3, 0,3);
    }
    public static final State startState = new State(3,0);
    public static final State goalState  = new State(0,3);
    public static int heuristic(State s) {
        return Math.abs(s.row - goalState.row) + Math.abs(s.col - goalState.col);
    }
    public static State[] aStar(State start, State goal) {
        int numNodes = rows * cols;
        int startNode = id(start.row, start.col);
        int goalNode  = id(goal.row, goal.col);

        int[] costFromStart = new int[numNodes];
        Arrays.fill(costFromStart, Integer.MAX_VALUE);
        costFromStart[startNode] = 0;

        int[] cameFrom = new int[numNodes];
        Arrays.fill(cameFrom, -1);

        boolean[] visited = new boolean[numNodes];

        PriorityQueue<PQItem> myQueue = new PriorityQueue<PQItem>();
        myQueue.add(new PQItem(heuristic(start), startNode));

        while (!myQueue.isEmpty()) {
            PQItem item = myQueue.poll();
            int current = item.id;
            if (visited[current]) {
                continue;
            } else{
                visited[current] = true;
            }
            if (current == goalNode) {
                return rebuildPath(cameFrom, startNode, goalNode);
            }
            ArrayList<Integer> neighbors = myList.neighbors(current);
            for (int i = 0; i < neighbors.size(); i++) {
                int next = neighbors.get(i);
                if (visited[next]) {
                    continue;
                }
                int newCost = costFromStart[current] + 1;
                if (newCost < costFromStart[next]) {
                    costFromStart[next] = newCost;
                    cameFrom[next] = current;

                    State nextState = new State(rowID(next), colID(next));
                    int priority = costFromStart[next] + heuristic(nextState);

                    myQueue.add(new PQItem(priority, next));
                }
            }
        }
        return null;
    }
    public static State[] rebuildPath(int[] parent, int startId, int goalId) {
        ArrayList<State> rev = new ArrayList<State>();
        int cur = goalId;
        while (cur != -1) {
            rev.add(new State(rowID(cur), colID(cur)));
            if (cur == startId) break;
            cur = parent[cur];
        }
        if (cur != startId) return null;
        Collections.reverse(rev);
        return rev.toArray(new State[0]);
    }
    public static void printPath(State[] path) {
        System.out.print("Path: ");
        for (int i = 0; i < path.length; i++) {
            System.out.print(path[i]);
            if (i + 1 < path.length){
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }
    public static final class PQItem implements Comparable<PQItem> {
        int f;
        int id;
        PQItem(int f, int id) {
            this.f = f; this.id = id;
        }
        public int compareTo(PQItem o) {
            if (this.f < o.f) return -1;
            if (this.f > o.f) return 1;
            if (this.id < o.id) return -1;
            if (this.id > o.id) return 1;
            return 0;
        }
    }
    public static void main(String[] args) {
        buildGraph();
        State[] path = aStar(startState, goalState);
        System.out.println("Starting point: " + startState);
        System.out.println("Goal point: " + goalState);

        if (path == null) System.out.println("No path found.");
        else printPath(path);
    }
    //
}
//path should be 03 -> 13 -> 23 -> 22 -> 12 -> 02 -> 01 -> 11 -> 21 -> 31 -> 30
