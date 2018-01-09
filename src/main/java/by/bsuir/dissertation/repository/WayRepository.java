package by.bsuir.dissertation.repository;

import by.bsuir.dissertation.entity.Way;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WayRepository extends MongoRepository<Way, Long> {
}
