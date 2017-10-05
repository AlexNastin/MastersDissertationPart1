package by.bsuir.dissertation;

import by.bsuir.dissertation.parse.OSMParser;
import by.bsuir.dissertation.repository.NodeRepository;
import org.neo4j.ogm.config.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

@SpringBootApplication
@EnableNeo4jRepositories
@EnableTransactionManagement
public class DissertationApplication {

	@Bean
	public Configuration getConfiguration() {
		Configuration config = new Configuration();
		config.driverConfiguration()
				.setDriverClassName("org.neo4j.ogm.drivers.http.driver.HttpDriver")
				.setURI("http://neo4j:root@localhost:7474");
		return config;
	}
//
//	@Bean
//	public SessionFactory getSessionFactory() {
//		return new SessionFactory(getConfiguration(), "by.bsuir.dissertation.entity");
//	}
//
//	@Bean
//	@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
//	public Session getSession() throws Exception {
//		return getSession();
//	}


	//	@Bean
//	public SessionFactory getSessionFactory() {
//		log.info("Initialising Session Factory");
//		System.setProperty("username", "neo4j");
//		System.setProperty("password", "root");
//		return new SessionFactory("by.bsuir.dissertation.entity");
//	}
	private final static Logger log = LoggerFactory.getLogger(DissertationApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(DissertationApplication.class, args);
		log.info("FINISH");
	}


    @Bean
    CommandLineRunner demo(NodeRepository nodeRepository) {
        return args -> {
			nodeRepository.deleteAll();
			try {
				SAXParserFactory parserFactor = SAXParserFactory.newInstance();
				SAXParser parser = parserFactor.newSAXParser();
				OSMParser handler = new OSMParser();
				File file = new ClassPathResource("map.xml").getFile();
				parser.parse(file, handler);
				log.info("Size: " + handler.getNodes().size());
				handler.getNodes().forEach(nodeRepository::save);
			} catch (SAXException | ParserConfigurationException | IOException e) {
				log.error("", e);
			}
		};
	}

}
