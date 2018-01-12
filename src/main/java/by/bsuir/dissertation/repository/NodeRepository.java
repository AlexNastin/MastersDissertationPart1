package by.bsuir.dissertation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import by.bsuir.dissertation.entity.graph.Node;

@Repository
public interface NodeRepository extends MongoRepository<Node, String> {
}