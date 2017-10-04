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

    private double distanceEdge;

    @Relationship(type = "NODES", direction = Relationship.UNDIRECTED)
    private Node nodeStart;

    @Relationship(type = "NODES", direction = Relationship.UNDIRECTED)
    private Node nodeEnd;

    public Edge(double distanceEdge, Node nodeStart, Node nodeEnd) {
        this.distanceEdge = distanceEdge;
        this.nodeStart = nodeStart;
        this.nodeEnd = nodeEnd;
    }

    public Edge() {
    }
}
