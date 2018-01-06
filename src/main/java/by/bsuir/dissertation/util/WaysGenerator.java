package by.bsuir.dissertation.util;

import by.bsuir.dissertation.entity.general.Graph;
import by.bsuir.dissertation.entity.graph.Edge;
import by.bsuir.dissertation.entity.graph.Node;
import by.bsuir.dissertation.entity.mongo.Way;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
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
        org.jgrapht.Graph<Node, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);
        nodes.forEach(g::addVertex);
        edges.forEach(edge -> {
            g.addEdge(edge.getNodeA(), edge.getNodeB());
        });
        DijkstraShortestPath<Node, DefaultEdge> dijkstraShortestPath = new DijkstraShortestPath(g);
        System.out.println("INIT");
        GraphPath<Node, DefaultEdge> path = dijkstraShortestPath.getPath(nodes.get(3), nodes.get(5));
        path.getVertexList().forEach(System.out::println);

        return null;
    }

    private void init(Graph graph) {
        System.out.println("INIT");
        nodes = graph.getNodes();
        edges = graph.getEdges();

    }
}
