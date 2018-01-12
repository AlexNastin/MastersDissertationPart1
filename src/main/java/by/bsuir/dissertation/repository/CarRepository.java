package by.bsuir.dissertation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import by.bsuir.dissertation.entity.Car;

public interface CarRepository extends MongoRepository<Car, String> {
}
