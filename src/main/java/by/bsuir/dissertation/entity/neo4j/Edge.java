package by.bsuir.dissertation.entity.neo4j;

import lombok.Getter;
import lombok.Setter;
import org.jgrapht.graph.DefaultEdge;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NodeEntity
public class Edge extends DefaultEdge{

    @GraphId
    private Long id;

    private String uuid;

    private double distance;

    @Relationship(type = "NODES", direction = Relationship.UNDIRECTED)
    private Node nodeA;

    @Relationship(type = "NODES", direction = Relationship.UNDIRECTED)
    private Node nodeB;

    public Edge() {
        this.uuid = UUID.randomUUID().toString();
    }

    public Edge(double distance) {
        this();
        this.distance = distance;
    }

    public Edge(double distance, Node nodeA, Node nodeB) {
        this(distance);
        this.nodeA = nodeA;
        this.nodeB = nodeB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;
        Edge edge = (Edge) o;
        return Double.compare(edge.distance, distance) == 0 &&
                Objects.equals(id, edge.id) &&
                Objects.equals(uuid, edge.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, distance);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Edge{");
        sb.append("id=").append(id);
        sb.append(", uuid='").append(uuid).append('\'');
        sb.append(", distance=").append(distance);
        sb.append('}');
        return sb.toString();
    }
}
