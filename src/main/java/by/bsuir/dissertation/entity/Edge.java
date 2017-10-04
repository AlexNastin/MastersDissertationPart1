package by.bsuir.dissertation.entity;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

@NodeEntity
@Getter
@Setter
public class Edge {

    @GraphId
    private Long id;

    private double longEdge;

    @Relationship(type = "NODES", direction = Relationship.UNDIRECTED)
    private Set<Node> nodes;

    public Edge(double longEdge) {
        this.longEdge = longEdge;
    }

    public Edge() {
    }
}
