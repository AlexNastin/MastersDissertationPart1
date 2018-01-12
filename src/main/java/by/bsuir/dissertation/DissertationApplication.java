package by.bsuir.dissertation;

import by.bsuir.dissertation.configuration.GeneralConfiguration;
import by.bsuir.dissertation.configuration.MongoConfiguration;
import by.bsuir.dissertation.entity.graph.Graph;
import by.bsuir.dissertation.manager.TrafficManager;
import by.bsuir.dissertation.manager.WaysManager;
import by.bsuir.dissertation.repository.CarRepository;
import by.bsuir.dissertation.repository.EdgeRepository;
import by.bsuir.dissertation.repository.NodeRepository;
import by.bsuir.dissertation.repository.WayRepository;
import by.bsuir.dissertation.service.CarService;
import by.bsuir.dissertation.service.GraphService;
import by.bsuir.dissertation.service.ParserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({MongoConfiguration.class, GeneralConfiguration.class})
public class DissertationApplication {

    private final static Logger LOGGER = LoggerFactory.getLogger(DissertationApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DissertationApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(GraphService graphService, CarService carService, CarRepository carRepository, WayRepository wayRepository, NodeRepository nodeRepository, EdgeRepository edgeRepository, ParserService parserService, WaysManager waysManager, TrafficManager trafficManager) {
        return args -> {
            LOGGER.info("DELETING DATA");
            nodeRepository.deleteAll();
            edgeRepository.deleteAll();
            wayRepository.deleteAll();
            carRepository.deleteAll();
            Graph graph = parserService.parseData();
            LOGGER.info("SAVE GRAPH");
            graphService.saveGraph(graph);
            LOGGER.info("SAVE GRAPH COMPLETE");
            waysManager.run();
            LOGGER.info("GENERATE CARS");
            carService.createCars();
            LOGGER.info("GENERATE CARS COMPLETE");
            LOGGER.info("START CARS");
            trafficManager.run();
        };
    }
}
