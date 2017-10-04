package by.bsuir.dissertation.repository;

import by.bsuir.dissertation.entity.Node;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface NodeRepository extends GraphRepository<Node> {
    Node findByTitle(String title);
}