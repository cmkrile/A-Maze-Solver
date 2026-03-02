import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class AdjacencyList {
    private final Map<Integer, ArrayList<Integer>> adj;

    public AdjacencyList() {
        this.adj = new HashMap<Integer, ArrayList<Integer>>();
    }

    public void addVertex(int v) {
        if (!adj.containsKey(v)) {
            adj.put(v, new ArrayList<Integer>());
        }
    }

    public void addEdge(int x, int y) {
        addVertex(x);
        addVertex(y);
        ArrayList<Integer> listU = adj.get(x);
        listU.add(y);
        ArrayList<Integer> listV = adj.get(y); // undirected
        listV.add(x);
    }

    public ArrayList<Integer> neighbors(int v) {
        ArrayList<Integer> list = adj.get(v);
        if (list == null) {
            return new ArrayList<Integer>();
        }
        return list;
    }
}