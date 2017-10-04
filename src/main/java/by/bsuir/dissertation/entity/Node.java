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

    private String title;

    @Relationship(type = "NODES", direction = Relationship.UNDIRECTED)
    private Set<Node> nodes;

    public Node(String title) {
        this.title = title;
    }

    public Node() {
    }

    public void worksWith(Node node) {
        if (nodes == null) {
            nodes = new HashSet<>();
        }
        nodes.add(node);

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Node{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(id, node.id) &&
                Objects.equals(title, node.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}