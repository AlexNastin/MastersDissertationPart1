package by.bsuir.dissertation;

import by.bsuir.dissertation.entity.Edge;
import by.bsuir.dissertation.entity.Node;
import by.bsuir.dissertation.repository.NodeRepository;
import org.neo4j.ogm.config.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;
import java.util.List;

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
	}


	@Bean
	CommandLineRunner demo(NodeRepository nodeRepository) {
		return args -> {

			nodeRepository.deleteAll();

			Node greg = new Node("Greg");
			Node roy = new Node("Roy");
			Node craig = new Node("Craig");

			List<Node> team = Arrays.asList(greg, roy, craig);

			Edge x = new Edge(12);
			Edge y = new Edge(78);
			Edge z = new Edge(16);

			List<Edge> team1 = Arrays.asList(x, y, z);

			log.info("Before linking up with Neo4j...");

			team.stream().forEach(person -> log.info("\t" + person.toString()));

//			nodeRepository.save(greg);
//			nodeRepository.save(roy);
//			nodeRepository.save(craig);

//			greg = nodeRepository.findByTitle(greg.getTitle());
			greg.worksWith(x);
			greg.worksWith(y);
			nodeRepository.save(greg);

//			roy = nodeRepository.findByTitle(roy.getTitle());
			roy.worksWith(z);
			roy.worksWith(y);
			// We already know that roy works with greg
			nodeRepository.save(roy);

			// We already know craig works with roy and greg
//			craig = nodeRepository.findByTitle(craig.getTitle());
			craig.worksWith(y);
			nodeRepository.save(craig);

			log.info("Lookup each person by name...");
			team.stream().forEach(person -> log.info(
					"\t" + nodeRepository.findByTitle(person.getTitle()).toString()));
		};
	}

}
