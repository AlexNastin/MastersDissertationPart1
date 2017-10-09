package by.bsuir.dissertation.parse;

import by.bsuir.dissertation.entity.Node;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class OSMWay {

    private String region;
    private List<Node> nodes = new ArrayList<>();

    public void addNode(Node node) {
        this.nodes.add(node);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OSMWay)) return false;
        OSMWay osmWay = (OSMWay) o;
        return Objects.equals(nodes, osmWay.nodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodes);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OSMWay{");
        sb.append("nodes=").append(nodes);
        sb.append('}');
        return sb.toString();
    }
}
