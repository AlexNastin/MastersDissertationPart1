package by.bsuir.dissertation.configuration;

import org.neo4j.ogm.config.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableNeo4jRepositories(basePackages = "by.bsuir.dissertation.repository.neo4j")
@EnableTransactionManagement
public class Neo4jConfiguration {

    @Value("${db.neo4j.driver-class-name}")
    private String driverClassName;

    @Value("${db.neo4j.uri}")
    private String uri;

    @Bean
    public Configuration getConfiguration() {
        Configuration config = new Configuration();
        config.driverConfiguration().setDriverClassName(driverClassName).setURI(uri);
        return config;
    }
}
