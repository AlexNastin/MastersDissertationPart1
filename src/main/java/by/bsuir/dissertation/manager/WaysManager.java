package by.bsuir.dissertation.manager;

import by.bsuir.dissertation.entity.general.Graph;
import by.bsuir.dissertation.repository.mongo.WayRepository;
import by.bsuir.dissertation.service.GraphService;
import by.bsuir.dissertation.util.WaysGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WaysManager {

    private final GraphService graphService;

    private final WaysGenerator waysGenerator;

    private final WayRepository wayRepository;

    private Graph graph;

    @Autowired
    public WaysManager(GraphService graphService, WaysGenerator waysGenerator, WayRepository wayRepository) {
        this.graphService = graphService;
        this.waysGenerator = waysGenerator;
        this.wayRepository = wayRepository;
        this.graph = graphService.getGraph();
    }

    public void generateWays() {
        //todo
    }
}
