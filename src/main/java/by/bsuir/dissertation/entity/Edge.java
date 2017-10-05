package by.bsuir.dissertation.entity;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Objects;

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

    public Edge() {
    }

    public Edge(double distanceEdge, Node nodeStart, Node nodeEnd) {
        this.distanceEdge = distanceEdge;
        this.nodeStart = nodeStart;
        this.nodeEnd = nodeEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return Double.compare(edge.distanceEdge, distanceEdge) == 0 &&
                Objects.equals(id, edge.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, distanceEdge);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Edge{");
        sb.append("id=").append(id);
        sb.append(", distanceEdge=").append(distanceEdge);
        sb.append('}');
        return sb.toString();
    }
}
