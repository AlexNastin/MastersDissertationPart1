package by.bsuir.dissertation.service;

import by.bsuir.dissertation.entity.graph.Graph;

public interface GraphService {
    Graph getGraph();

    void saveGraph(Graph graph);
}
