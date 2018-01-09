package by.bsuir.dissertation.service;

import by.bsuir.dissertation.entity.graph.Graph;
import by.bsuir.dissertation.parser.OSMParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParser;
import java.io.File;
import java.io.IOException;

@Service
public class DefaultParserService implements ParserService {

    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultParserService.class);

    private static final String MAP_XML = "map.xml";

    private final SAXParser parser;
    private final OSMParser handler;

    @Autowired
    public DefaultParserService(SAXParser parser, OSMParser handler) {
        this.parser = parser;
        this.handler = handler;
    }

    public Graph parseData() {
        Graph graph = null;
        try {
            File file = new ClassPathResource(MAP_XML).getFile();
            LOGGER.info("START PARSE MAP");
            parser.parse(file, handler);
            graph = new Graph(handler.getEdges(), handler.getNodes());
            LOGGER.info("FINISHED PARSE MAP");
        } catch (IOException | SAXException e) {
            LOGGER.error("PARSE ERROR", e);
        }
        return graph;
    }
}
