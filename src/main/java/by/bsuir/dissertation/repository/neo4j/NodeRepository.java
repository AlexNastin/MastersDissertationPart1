package by.bsuir.dissertation.repository.neo4j;

import by.bsuir.dissertation.entity.neo4j.Node;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeRepository extends GraphRepository<Node> {
}