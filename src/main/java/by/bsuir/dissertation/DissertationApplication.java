package by.bsuir.dissertation;

import by.bsuir.dissertation.configuration.MongoConfiguration;
import by.bsuir.dissertation.configuration.Neo4jConfiguration;
import by.bsuir.dissertation.parse.OSMParser;
import by.bsuir.dissertation.repository.neo4j.EdgeRepository;
import by.bsuir.dissertation.repository.neo4j.NodeRepository;
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
@Import({Neo4jConfiguration.class, MongoConfiguration.class})
public class DissertationApplication {

    private final static Logger LOGGER = LoggerFactory.getLogger(DissertationApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DissertationApplication.class, args);
        LOGGER.info("FINISHED FULL PROCESS");
    }

    @Bean
    public CommandLineRunner demo(NodeRepository nodeRepository, EdgeRepository edgeRepository) {
        return args -> {
            nodeRepository.deleteAll();
            edgeRepository.deleteAll();
            LOGGER.info("DELETE FINISHED");
            try {
                LOGGER.info("START PARSE MAP");
                SAXParserFactory parserFactor = SAXParserFactory.newInstance();
                SAXParser parser = parserFactor.newSAXParser();
                OSMParser handler = new OSMParser();
                File file = new ClassPathResource("map.xml").getFile();
                parser.parse(file, handler);
                LOGGER.info("FINISHED PARSE MAP");
                LOGGER.info("Size: " + handler.getNodes().size());
                handler.getNodes().forEach(node -> nodeRepository.save(node, 1));
                handler.getEdges().forEach(edge -> edgeRepository.save(edge, 1));
            } catch (SAXException | ParserConfigurationException | IOException e) {
                LOGGER.error("ERROR: ", e);
            }
        };
    }
}
