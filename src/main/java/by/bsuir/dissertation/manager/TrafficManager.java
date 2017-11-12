package by.bsuir.dissertation.manager;

import by.bsuir.dissertation.entity.general.Car;
import by.bsuir.dissertation.entity.mongo.Way;
import by.bsuir.dissertation.manager.thread.CarMovementThread;

public class TrafficManager {

    public TrafficManager() {
    }

    public TrafficManager(Car car, Way way) {
        CarMovementThread carMovementThread = new CarMovementThread(car, way);
        carMovementThread.start();
    }
}
