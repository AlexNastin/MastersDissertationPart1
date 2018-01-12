package by.bsuir.dissertation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import by.bsuir.dissertation.entity.ResultData;

public interface ResultDataRepository extends MongoRepository<ResultData, String> {
}
