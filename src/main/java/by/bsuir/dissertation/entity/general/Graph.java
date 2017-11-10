package by.bsuir.dissertation.entity.general;

import by.bsuir.dissertation.entity.neo4j.Edge;
import by.bsuir.dissertation.entity.neo4j.Node;

import java.util.List;

public class Graph {

    private final List<Edge> edges;
    private final List<Node> nodes;

    public Graph(List<Node> nodes, List<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public List<Node> getVertexes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
