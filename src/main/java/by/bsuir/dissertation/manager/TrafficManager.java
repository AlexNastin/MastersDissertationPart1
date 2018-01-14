package by.bsuir.dissertation.manager;

import by.bsuir.dissertation.entity.Car;
import by.bsuir.dissertation.entity.Way;
import by.bsuir.dissertation.repository.CarRepository;
import by.bsuir.dissertation.repository.WayRepository;
import by.bsuir.dissertation.util.TrafficGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class TrafficManager implements Runnable {

    private final static Logger LOGGER = LoggerFactory.getLogger(TrafficManager.class);

    @Value("${cars.quantity}")
    private int carQuantity;

    private ApplicationContext applicationContext;

    private WayRepository wayRepository;
    private CarRepository carRepository;

    private List<TrafficGenerator> trafficGenerators = new ArrayList<>();

    private final TaskExecutor taskExecutor;

    @Autowired
    public TrafficManager(ApplicationContext applicationContext, WayRepository wayRepository, CarRepository carRepository, TaskExecutor trafficManagerTaskExecutor) {
        this.applicationContext = applicationContext;
        this.wayRepository = wayRepository;
        this.carRepository = carRepository;
        this.taskExecutor = trafficManagerTaskExecutor;
    }

    private void initialize() {
        List<Car> cars = carRepository.findAll();
        List<Way> ways = wayRepository.findAll();
        Random random = new Random();
        for (Car car : cars) {
            TrafficGenerator trafficGenerator = applicationContext.getBean(TrafficGenerator.class);
            trafficGenerator.setCar(car);
            trafficGenerator.setWay(ways.get(random.nextInt(ways.size())).getFullWay());
            trafficGenerators.add(trafficGenerator);
        }
    }

    private void execute() {
        for (TrafficGenerator trafficGenerator : trafficGenerators) {
            taskExecutor.execute(trafficGenerator);
        }
    }

    @Override
    public void run() {
        initialize();
        boolean isWork = true;
        LOGGER.info("START CARS");
        execute();
        while (isWork) {
            for (int i = 0; i < trafficGenerators.size(); i++) {
                TrafficGenerator trafficGenerator = trafficGenerators.get(i);
                if (trafficGenerator.isDone()) {
                    trafficGenerators.remove(trafficGenerator);
                }
            }
            if (trafficGenerators.size() == 0) {
                if (((ThreadPoolTaskExecutor) taskExecutor).getActiveCount() == 0) {
                    ((ThreadPoolTaskExecutor) taskExecutor).shutdown();
                }
                isWork = false;
                LOGGER.info("FINISH CARS");
            }
        }
    }
}
