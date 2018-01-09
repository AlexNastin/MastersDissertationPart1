package by.bsuir.dissertation.entity.graph;

import by.bsuir.dissertation.entity.graph.Edge;
import by.bsuir.dissertation.entity.graph.Node;
import lombok.Data;

import java.util.List;

@Data
public class Graph {

    private final List<Edge> edges;
    private final List<Node> nodes;
}
