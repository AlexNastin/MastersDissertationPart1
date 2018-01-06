package by.bsuir.dissertation;

import by.bsuir.dissertation.configuration.MongoConfiguration;
import by.bsuir.dissertation.entity.general.Graph;
import by.bsuir.dissertation.parse.OSMParser;
import by.bsuir.dissertation.repository.EdgeRepository;
import by.bsuir.dissertation.repository.NodeRepository;
import by.bsuir.dissertation.service.GraphService;
import by.bsuir.dissertation.util.WaysGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

@SpringBootApplication
@Import(MongoConfiguration.class)
public class DissertationApplication {

    private final static Logger LOGGER = LoggerFactory.getLogger(DissertationApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DissertationApplication.class, args);
        LOGGER.info("FINISHED FULL PROCESS");
    }

    @Bean
    public CommandLineRunner demo(NodeRepository nodeRepository, EdgeRepository edgeRepository, WaysGenerator waysGenerator, GraphService graphService) {
        return args -> {
//            try {
//                nodeRepository.deleteAll();
//                edgeRepository.deleteAll();
//                LOGGER.info("START PARSE MAP");
//                SAXParserFactory parserFactor = SAXParserFactory.newInstance();
//                SAXParser parser = parserFactor.newSAXParser();
//                OSMParser handler = new OSMParser();
//                File file = new ClassPathResource("map.xml").getFile();
//                parser.parse(file, handler);
//                LOGGER.info("FINISHED PARSE MAP");
//                LOGGER.info("Size: " + handler.getNodes().size());
//                nodeRepository.save(handler.getNodes());
//                edgeRepository.save(handler.getEdges());
//                LOGGER.info("FINISHED SAVE DATA");
//            } catch (SAXException | ParserConfigurationException | IOException e) {
//                LOGGER.error("ERROR: ", e);
//            }
            Graph graph = graphService.getGraph();
            System.out.println("Graph: " + graph);
            waysGenerator.generateWay(graph);
        };
    }
}
