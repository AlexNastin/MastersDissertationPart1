package by.bsuir.dissertation.configuration;


import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableNeo4jRepositories(basePackages = "by.bsuir.dissertation.repository.neo4j")
@EnableTransactionManagement
public class Neo4jConfiguration {

    private static final String PACKAGE_NEO4J_ENTITY_LOCATION = "by.bsuir.dissertation.entity.neo4j";

    @Value("${db.neo4j.driver-class-name}")
    private String driverClassName;

    @Value("${db.neo4j.uri}")
    private String uri;

    @Bean
    public SessionFactory getSessionFactory() {
        return new SessionFactory(configuration(), PACKAGE_NEO4J_ENTITY_LOCATION);
    }

    @Bean
    public Neo4jTransactionManager transactionManager() throws Exception {
        return new Neo4jTransactionManager(getSessionFactory());
    }

    @Bean
    public org.neo4j.ogm.config.Configuration configuration() {
        org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
        config.driverConfiguration().setDriverClassName(driverClassName).setURI(uri);
        return config;
    }
}
