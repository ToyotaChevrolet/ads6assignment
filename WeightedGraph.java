
import java.util.*;

public class WeightedGraph<V> {

    private final boolean undirected;
    private Map<V, Vertex<V>> map;

    public WeightedGraph() {
        this.undirected = true;
        map = new HashMap<>();
    }

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
        map = new HashMap<>();
    }

    public void addVertex(V v) {
        if (hasVertex(v)) return;
        map.put(v, new Vertex<>(v));
    }

    public void addEdge(V source, V dest, double weight) {
        if (!hasVertex(source)) {
            addVertex(source);
        }
        if (!hasVertex(dest)) {
            addVertex(dest);
        }
        if (hasEdge(source, dest) || source.equals(dest)) {
            return;
        }

        map.get(source).addAdjacentVertex(map.get(dest), weight);

        if (undirected) {
            map.get(dest).addAdjacentVertex(map.get(source), weight);
        }
    }

    public boolean hasVertex(V v) {
        return map.containsKey(v);
    }

    public boolean hasEdge(V source, V dest) {
        if (!hasVertex(source)) return false;
        return map.get(source).getAdjacentVertices().containsKey(map.get(dest));
    }

    public Iterable<V> adjacencyList(V v) {
        if (!hasVertex(v)) return null;
        List<V> vertices = new LinkedList<>();
        for (Vertex<V> vertex: map.get(v).getAdjacentVertices().keySet()) {
            vertices.add(vertex.getData());
        }
        return vertices;
    }

    public Iterable<Vertex<V>> getEdges(V vertex) {
        if (!hasVertex(vertex)) return null;
        return map.get(vertex).getAdjacentVertices().keySet();
    }

    public Vertex<V> getVertex(V v) {
        return map.get(v);
    }
}
