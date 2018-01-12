package by.bsuir.dissertation.util;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import by.bsuir.dissertation.entity.Car;
import by.bsuir.dissertation.entity.Way;
import by.bsuir.dissertation.manager.thread.CarMovementThread;

@Component
@Scope("prototype")
public class TrafficGenerator {

    public TrafficGenerator() {
    }

    public TrafficGenerator(Car car, Way way) {
        CarMovementThread carMovementThread = new CarMovementThread(car, way);
        carMovementThread.start();
    }
}
