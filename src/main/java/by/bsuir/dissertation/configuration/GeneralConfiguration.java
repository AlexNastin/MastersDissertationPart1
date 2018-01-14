package by.bsuir.dissertation.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

@Configuration
public class GeneralConfiguration {

    private final static Logger LOGGER = LoggerFactory.getLogger(GeneralConfiguration.class);

    @Value("${cars.quantity}")
    private int carsQuantity;

    @Bean
    public SAXParserFactory saxParserFactory() {
        return SAXParserFactory.newInstance();
    }

    @Bean
    public SAXParser saxParser(SAXParserFactory saxParserFactory) {
        SAXParser saxParser = null;
        try {
            saxParser = saxParserFactory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            LOGGER.error("SAXParser CREATE ERROR", e);
        }
        return saxParser;
    }

    @Bean(name = "waysManagerTaskExecutor")
    public TaskExecutor waysManagerTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setThreadNamePrefix("waysManagerTaskExecutor-");
        executor.initialize();
        return executor;
    }

    @Bean(name = "trafficManagerTaskExecutor")
    public TaskExecutor trafficManagerTaskExecutor() {
        ThreadPoolTaskExecutor scheduler = new ThreadPoolTaskExecutor();
        scheduler.setCorePoolSize(carsQuantity);
        scheduler.setThreadNamePrefix("trafficManagerTaskExecutor-");
        scheduler.initialize();
        return scheduler;
    }
}
