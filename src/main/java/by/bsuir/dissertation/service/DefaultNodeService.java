package by.bsuir.dissertation.service;

import by.bsuir.dissertation.entity.graph.Node;
import by.bsuir.dissertation.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultNodeService implements NodeService {

    private final NodeRepository nodeRepository;

    @Autowired
    public DefaultNodeService(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    @Override
    public Node getNode(String id) {
        return nodeRepository.findOne(id);
    }
}
