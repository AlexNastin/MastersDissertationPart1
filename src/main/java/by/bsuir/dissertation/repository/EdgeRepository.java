package by.bsuir.dissertation.repository;

import by.bsuir.dissertation.entity.graph.Edge;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EdgeRepository extends MongoRepository<Edge, Long> {
}