package by.bsuir.dissertation.util;

import by.bsuir.dissertation.entity.general.Graph;
import by.bsuir.dissertation.entity.mongo.Way;
import by.bsuir.dissertation.entity.neo4j.Edge;
import by.bsuir.dissertation.entity.neo4j.Node;
import org.jgrapht.EdgeFactory;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.ClassBasedEdgeFactory;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WaysGenerator {

    private List<Node> nodes;
    private List<Edge> edges;

    public List<Way> generateWay(Graph graph) {
        init(graph);
        EdgeFactory edgeFactory = new ClassBasedEdgeFactory(Edge.class);

        UndirectedGraph<Node, DefaultEdge> newGraph = new SimpleGraph<>(DefaultEdge.class);
        edges.forEach(edge -> {
            newGraph.addEdge(edge.getNodeA(), edge.getNodeB(), edge);
        });
        return null;
    }

    private void init(Graph graph) {
        nodes = graph.getNodes();
        edges = graph.getEdges();
    }

}
