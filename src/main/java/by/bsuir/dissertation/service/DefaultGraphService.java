package by.bsuir.dissertation.service;

import by.bsuir.dissertation.entity.general.Graph;
import by.bsuir.dissertation.entity.graph.Edge;
import by.bsuir.dissertation.entity.graph.Node;
import by.bsuir.dissertation.repository.EdgeRepository;
import by.bsuir.dissertation.repository.NodeRepository;
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
        List<Node> nodes = nodeRepository.findAll();
        List<Edge> edges = edgeRepository.findAll();
        return new Graph(nodes, edges);
    }
}