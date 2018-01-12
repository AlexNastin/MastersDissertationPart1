package by.bsuir.dissertation.manager;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.bsuir.dissertation.entity.Car;
import by.bsuir.dissertation.entity.Way;
import by.bsuir.dissertation.repository.CarRepository;
import by.bsuir.dissertation.repository.WayRepository;
import by.bsuir.dissertation.util.TrafficGenerator;

@Component
public class TrafficManager implements Runnable {

    private final static Logger LOGGER = LoggerFactory.getLogger(TrafficManager.class);

    private WayRepository wayRepository;
    private CarRepository carRepository;

    private List<Car> cars;
    private List<Way> ways;

    @Autowired
    public TrafficManager(WayRepository wayRepository, CarRepository carRepository) {
        this.wayRepository = wayRepository;
        this.carRepository = carRepository;
    }

    @Override
    public void run() {
        generateTraffic();
    }

    private void generateTraffic() {
        // test logic
        cars = carRepository.findAll();
        ways = wayRepository.findAll();

        int count = 1;
        for (Way way : ways) {
            LOGGER.info("Start " + count + " way");
            TrafficGenerator trafficGenerator = new TrafficGenerator(cars.get(0), way);
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                LOGGER.error("", e);
            }
            count++;
        }
        // Todo: add generate logic
    }
}
