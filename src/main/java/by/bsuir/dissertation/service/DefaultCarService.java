package by.bsuir.dissertation.service;

import by.bsuir.dissertation.entity.Car;
import by.bsuir.dissertation.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DefaultCarService implements CarService {

    private final CarRepository carRepository;

    @Value("${cars.quantity}")
    private int quantity;

    @Autowired
    public DefaultCarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void createCars() {
        for (int i = 0; i < quantity; i++) {
            carRepository.save(new Car());
        }
    }

}
