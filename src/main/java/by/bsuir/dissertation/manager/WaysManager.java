package by.bsuir.dissertation.manager;

import by.bsuir.dissertation.entity.general.Graph;
import by.bsuir.dissertation.repository.WayRepository;
import by.bsuir.dissertation.service.GraphService;
import by.bsuir.dissertation.util.WaysGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class WaysManager {

    private final GraphService graphService;

    private final WaysGenerator waysGenerator;

    private final WayRepository wayRepository;

    private Graph graph;

    @Autowired
    public WaysManager(GraphService graphService, WaysGenerator waysGenerator, WayRepository wayRepository) {
        this.graphService = graphService;
        this.waysGenerator = waysGenerator;
        this.wayRepository = wayRepository;
        this.graph = graphService.getGraph();
    }

    public void generateWays() {
        waysGenerator.generateWay(graph);

    }
//    List<Node> nodes = (List<Node>) nodeRepository.findAll();
//
//    Iterable<Edge> edges = edgeRepository.findAll();
//
//    UndirectedGraph<Node, DefaultEdge> newGraph = new SimpleGraph<>(DefaultEdge.class);
//                edges.forEach(edge -> {
//                    System.out.println("edge.getNodeA() " + edge.getNodeA());
//                    System.out.println("edge.getNodeB() " + edge.getNodeB());
//                    newGraph.addEdge(edge.getNodeA(), edge.getNodeB(), edge);
//                });
//                Set<Node> nodes = newGraph.vertexSet();
//                System.out.println(all.size()+ " all");
//                System.out.println(nodes.size() + " nodes");
//
//                nodes.forEach(newGraph::addVertex);
//
//    Set<Node> nodesSet = newGraph.vertexSet();
//                System.out.println(nodesSet.size()+ " nodesSet");
//                System.out.println(nodes.size() + " nodes");
//
//    DefaultEdge edge = newGraph.getEdge(nodes.get(3), nodes.get(5));
//
//                System.out.println(edge + "  edge");

    //    public static Graph<String, DefaultEdge> createSamplegraph()
//    {
//        UndirectedGraph<String, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);
//
//        String v1 = "v1";
//        String v2 = "v2";
//        String v3 = "v3";
//        String v4 = "v4";
//
//        // add the vertices
//        g.addVertex(v1);
//        g.addVertex(v2);
//        g.addVertex(v3);
//        g.addVertex(v4);
//
//        // add edges to create a circuit
//        g.addEdge(v1, v2);
//        g.addEdge(v2, v3);
//        g.addEdge(v3, v4);
//        g.addEdge(v4, v1);
//
//        return g;
//    }


//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Set;
//import org.apache.commons.collections4.CollectionUtils;
//import org.jgrapht.DirectedGraph;
//import org.jgrapht.alg.TransitiveReduction;
//import org.jgrapht.graph.DefaultDirectedGraph;
//import org.jgrapht.graph.DefaultEdge;
//
//    public class WorkflowStepUtils {
//
//        public static void removeRedundantRelationships(Collection<WorkflowStep> workflowSteps) {
//
//            DirectedGraph<String, DefaultEdge> graph = new DefaultDirectedGraph<>(DefaultEdge.class);
//            workflowSteps.forEach(workflowStep -> graph.addVertex(workflowStep.getName()));
//            for (WorkflowStep workflowStep : workflowSteps) {
//                if (CollectionUtils.isNotEmpty(workflowStep.getFollowingSteps())) {
//                    workflowStep.getFollowingSteps().forEach(step -> graph.addEdge(workflowStep.getName(), step));
//                }
//            }
//
//            TransitiveReduction.INSTANCE.reduce(graph);
//
//            for (WorkflowStep step : workflowSteps) {
//                List<String> followingSteps = new ArrayList<>();
//                Set<DefaultEdge> edges = graph.outgoingEdgesOf(step.getName());
//                for (DefaultEdge edge : edges) {
//                    followingSteps.add(graph.getEdgeTarget(edge));
//                }
//                step.setFollowingSteps(followingSteps);
//            }
//        }
//    }
//
//    List<Node> nodes = (List<Node>) nodeRepository.findAll();
//
//    Iterable<Edge> edges = edgeRepository.findAll();
//
//    UndirectedGraph<Node, Edge> newGraph = new SimpleGraph<>(Edge.class);
//                edges.forEach(edge -> {
//        System.out.println("edge.getNodeA() " + edge.getNodeA());
//        System.out.println("edge.getNodeB() " + edge.getNodeB());
//        if (edge.getNodeA() != null && edge.getNodeB() != null){
//
//            newGraph.addEdge(edge.getNodeA(), edge.getNodeB(), edge);
//        }
//    });
//    Set<Node> nodesNew = newGraph.vertexSet();
//                System.out.println(nodesNew.size()+ " all");
//                System.out.println(nodes.size() + " nodes");
//
//                nodes.forEach(newGraph::addVertex);
//
//    Set<Node> nodesSet = newGraph.vertexSet();
//                System.out.println(nodesSet.size()+ " nodesSet");
//                System.out.println(nodes.size() + " nodes");
//
//    DefaultEdge edge = newGraph.getEdge(nodes.get(3), nodes.get(5));
//
//
//                System.out.println(edge + "  edge");
}
