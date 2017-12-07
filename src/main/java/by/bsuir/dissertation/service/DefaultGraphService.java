package by.bsuir.dissertation.service;

import by.bsuir.dissertation.entity.general.Graph;
import by.bsuir.dissertation.entity.neo4j.Edge;
import by.bsuir.dissertation.entity.neo4j.Node;
import by.bsuir.dissertation.repository.neo4j.EdgeRepository;
import by.bsuir.dissertation.repository.neo4j.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultGraphService implements GraphService {

    private final NodeRepository nodeRepository;

    private final EdgeRepository edgeRepository;

    @Autowired
    public DefaultGraphService(NodeRepository nodeRepository, EdgeRepository edgeRepository) {
        this.nodeRepository = nodeRepository;
        this.edgeRepository = edgeRepository;
    }

    @Override
    public Graph getGraph() {
        List<Node> nodes = (List<Node>) nodeRepository.findAll();
        List<Edge> edges = (List<Edge>) edgeRepository.findAll();
        return new Graph(nodes, edges);
    }
}
