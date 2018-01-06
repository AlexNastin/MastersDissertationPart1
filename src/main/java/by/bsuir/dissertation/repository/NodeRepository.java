package by.bsuir.dissertation.repository;

import by.bsuir.dissertation.entity.graph.Node;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeRepository extends MongoRepository<Node, Long> {
}