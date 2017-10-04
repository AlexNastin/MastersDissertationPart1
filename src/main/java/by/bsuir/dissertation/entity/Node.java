package by.bsuir.dissertation.entity;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NodeEntity
@Getter
@Setter
public class Node {

    @GraphId
    private Long id;
    private String number;
    private String latitude;
    private String longitude;

    @Relationship(type = "EDGES", direction = Relationship.UNDIRECTED)
    private Set<Edge> edges;

    public Node(String number, String latitude, String longitude) {
        this.number = number;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Node() {
    }

    public void worksWith(Edge edge) {
        if (edges == null) {
            edges = new HashSet<>();
        }
        edges.add(edge);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return Objects.equals(id, node.id) &&
                Objects.equals(number, node.number) &&
                Objects.equals(latitude, node.latitude) &&
                Objects.equals(longitude, node.longitude) &&
                Objects.equals(edges, node.edges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, latitude, longitude, edges);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Node{");
        sb.append("id=").append(id);
        sb.append(", number='").append(number).append('\'');
        sb.append(", latitude='").append(latitude).append('\'');
        sb.append(", longitude='").append(longitude).append('\'');
        sb.append(", edges=").append(edges);
        sb.append('}');
        return sb.toString();
    }
}