package by.bsuir.dissertation.entity;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Objects;

@Getter
@Setter
@NodeEntity
public class Edge {

    @GraphId
    private Long id;

    private double distance;

    @Relationship(type = "NODES", direction = Relationship.UNDIRECTED)
    private Node nodeA;

    @Relationship(type = "NODES", direction = Relationship.UNDIRECTED)
    private Node nodeB;

    public Edge() {
    }

    public Edge(double distance) {
        this.id = id;
        this.distance = distance;
    }

    public Edge(double distance, Node nodeA, Node nodeB) {
        this.distance = distance;
        this.nodeA = nodeA;
        this.nodeB = nodeB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return Double.compare(edge.distance, distance) == 0 &&
                Objects.equals(id, edge.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, distance);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Edge{");
        sb.append("id=").append(id);
        sb.append(", distance=").append(distance);
        sb.append('}');
        return sb.toString();
    }
}
