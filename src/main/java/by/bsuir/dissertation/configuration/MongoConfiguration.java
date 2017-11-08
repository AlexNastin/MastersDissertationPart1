package by.bsuir.dissertation.configuration;

import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "by.bsuir.dissertation.repository.mongo")
public class MongoConfiguration {

    @Value("${db.mongodb.uri}")
    private String uri;

    @Bean
    public MongoDbFactory getMongoDbFactory() throws Exception {
        MongoClientURI mongoClientURI = new MongoClientURI(uri);
        return new SimpleMongoDbFactory(mongoClientURI);
    }

    @Bean
    public MongoOperations getMongoTemplate() throws Exception {
        return new MongoTemplate(getMongoDbFactory());
    }
}
