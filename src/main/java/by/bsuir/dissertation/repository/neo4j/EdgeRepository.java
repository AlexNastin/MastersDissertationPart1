package by.bsuir.dissertation.repository.neo4j;

import by.bsuir.dissertation.entity.neo4j.Edge;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EdgeRepository extends GraphRepository<Edge> {
}