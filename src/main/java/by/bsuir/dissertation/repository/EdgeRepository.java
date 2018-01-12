package by.bsuir.dissertation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import by.bsuir.dissertation.entity.graph.Edge;

@Repository
public interface EdgeRepository extends MongoRepository<Edge, String> {
}