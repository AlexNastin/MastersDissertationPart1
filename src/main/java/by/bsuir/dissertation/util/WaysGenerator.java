package by.bsuir.dissertation.util;

import by.bsuir.dissertation.annotation.InjectGraph;
import by.bsuir.dissertation.entity.PartWay;
import by.bsuir.dissertation.entity.Way;
import by.bsuir.dissertation.entity.graph.Edge;
import by.bsuir.dissertation.entity.graph.Graph;
import by.bsuir.dissertation.entity.graph.Node;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.SimpleGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Scope("prototype")
public class WaysGenerator implements Runnable {

    private final static Logger LOGGER = LoggerFactory.getLogger(WaysGenerator.class);

    @InjectGraph
    private Graph graph;

    private boolean isDone;

    @Value("${ways-generator.ways.quantity}")
    private int quantity;

    private List<Way> ways = new ArrayList<>();

    @Override
    public void run() {
        generateWays();
    }

    private void generateWays() {
        org.jgrapht.Graph<Node, Edge> g = createJGraph();
        DijkstraShortestPath<Node, Edge> dijkstraShortestPath = new DijkstraShortestPath<>(g);
        for (int i = 0; i < quantity; i++) {
            Pair<Node, Node> nodesForCreatePath = getNodesForCreatePath();
            GraphPath<Node, Edge> path = dijkstraShortestPath.getPath(nodesForCreatePath.getFirst(), nodesForCreatePath.getSecond());
            loggingData(path);
            ways.add(createWay(path.getEdgeList(), path.getVertexList()));
        }
        this.isDone = true;
    }

    private Pair<Node, Node> getNodesForCreatePath() {
        Random random = new Random();
        int finalIndex = Math.abs(random.nextInt(graph.getNodes().size()));
        if (finalIndex == 0) {
            finalIndex++;
        }
        int startIndex = Math.abs(random.nextInt(finalIndex));
        Node nodeSource = graph.getNodes().get(startIndex);
        Node nodeSink = graph.getNodes().get(finalIndex);
        return Pair.of(nodeSource, nodeSink);
    }

    private org.jgrapht.Graph<Node, Edge> createJGraph() {
        org.jgrapht.Graph<Node, Edge> jGraph = new SimpleGraph<>(Edge.class);
        graph.getNodes().forEach(jGraph::addVertex);
        graph.getEdges().forEach(edge -> {
            jGraph.addEdge(edge.getNodeA(), edge.getNodeB(), edge);
        });
        return jGraph;
    }

    private Way createWay(List<Edge> edges, List<Node> nodes) {
        Way way = new Way();
        way.setNumberNodes(edges.size() + nodes.size());
        way.setName("");
        Map<Integer, PartWay> fullWay = new HashMap<>();
        int count = 0;
        for (Node node : nodes) {
            fullWay.put(count, createPartWay(node));
            count += 2;
        }
        count = 1;
        for (Edge edge : edges) {
            fullWay.put(count, createPartWay(edge));
            count += 2;
        }
        way.setFullWay(fullWay);
        loggingData(fullWay);
        return way;
    }

    private PartWay createPartWay(Object object) {
        PartWay partWay = new PartWay();
        if (object.getClass().equals(Node.class)) {
            Node node = (Node) object;
            partWay.setId(node.getId());
            partWay.setCamera(node.isCamera());
            partWay.setDistance(1);
            partWay.setRegion(node.getRegion());
        } else if (object.getClass().equals(Edge.class)) {
            Edge edge = (Edge) object;
            partWay.setId(edge.getId());
            partWay.setCamera(false);
            partWay.setDistance(edge.getDistance());
            partWay.setRegion(edge.getNodeA().getRegion());
        }
        return partWay;
    }

    public List<Way> getWays() {
        return ways;
    }

    public boolean isDone() {
        return isDone;
    }

    private void loggingData(Map<Integer, PartWay> fullWay) {
        fullWay.forEach((integer, partWay) -> {
            LOGGER.info(integer + " - " + partWay);
        });
    }

    private void loggingData(GraphPath<Node, Edge> path) {
        path.getEdgeList().forEach(edge -> LOGGER.info(String.valueOf(edge)));
        path.getVertexList().forEach(node -> LOGGER.info(String.valueOf(node)));
    }
}
