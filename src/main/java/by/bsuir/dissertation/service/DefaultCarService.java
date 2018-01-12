package by.bsuir.dissertation.service;

import by.bsuir.dissertation.entity.Car;
import by.bsuir.dissertation.repository.CarRepository;
import by.bsuir.dissertation.util.DissertationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultCarService implements CarService {


    private final CarRepository carRepository;

    @Autowired
    public DefaultCarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void createCars() {
        //todo CAR_MAX_COUNT move parameter to file
        for (int i = 0; i < DissertationConstants.CAR_MOVEMENT.CAR_MAX_COUNT; i++) {
            carRepository.save(new Car());
        }
    }

}
