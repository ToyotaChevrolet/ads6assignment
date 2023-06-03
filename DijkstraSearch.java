import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DijkstraSearch<V> extends Search<V> {

    private Set<V> unsettledNodes;
    private Map<V, Double> distances;
    private WeightedGraph<V> graph;

    public DijkstraSearch(WeightedGraph<V> graph, V source) {
        super(source);
        unsettledNodes = new HashSet<>();
        distances = new HashMap<>();
        this.graph = graph;
        dijkstra();
    }

    public void dijkstra() {
        distances.put(source, 0D);
        unsettledNodes.add(source);

        while (unsettledNodes.size() > 0) {
            V node = getVertexWithMinimumWeight(unsettledNodes);
            marked.add(node);
            unsettledNodes.remove(node);
            for (V target: graph.adjacencyList(node)) {
                if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) {
                    distances.put(target, getShortestDistance(node) + getDistance(node, target));
                    edgeTo.put(target, node);
                    unsettledNodes.add(target);
                }
            }
        }
    }

    private double getDistance(V node, V target) {
        Vertex<V> nodeV = graph.getVertex(node);
        for (Vertex<V> vertex: graph.getEdges(node)) {
            if (vertex.getData().equals(target)) {
                return nodeV.getAdjacentVertices().get(vertex);
            }
        }

        throw new RuntimeException("Not Found!");
    }

    private V getVertexWithMinimumWeight(Set<V> vertices) {
        V minimum = null;
        for (V vertex: vertices) {
            if (minimum == null) {
                minimum = vertex;
            }
            else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    private double getShortestDistance(V destination) {
        Double d = distances.get(destination);
        return (d == null ? Double.MAX_VALUE : d);
    }
 }
