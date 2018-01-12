package by.bsuir.dissertation.manager;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.bsuir.dissertation.entity.Car;
import by.bsuir.dissertation.entity.PartWay;
import by.bsuir.dissertation.entity.Way;
import by.bsuir.dissertation.util.DissertationConstants;

public class CarMovementThread extends Thread {

    private final static Logger LOGGER = LoggerFactory.getLogger(CarMovementThread.class);

    private Car car;
    private Map<Integer, PartWay> way;

    public CarMovementThread() {
    }

    public CarMovementThread(Car car, Way way) {
        this.car = car;
        this.way = way.getFullWay();
    }

    @Override
    public void run() {
        boolean isMaxSpeed = false;
        double speed = car.getSpeed();
        for (int i = 0; i < way.size(); i++) {
            PartWay partWay = way.get(i);
            double currentDistance = DissertationConstants.CAR_MOVEMENT.INITIAL_DISTANCE;
            while (currentDistance < partWay.getDistance()) {
                LOGGER.info(this.getName() + ": " + currentDistance);
                if (!isMaxSpeed) {
                    if (speed + DissertationConstants.CAR_MOVEMENT.ACCELERATION <= DissertationConstants.CAR_MOVEMENT.MAX_SPEED) {
                        speed += DissertationConstants.CAR_MOVEMENT.ACCELERATION;
                    } else {
                        speed = DissertationConstants.CAR_MOVEMENT.MAX_SPEED;
                        isMaxSpeed = true;
                    }
                }
                currentDistance += speed * DissertationConstants.CAR_MOVEMENT.SAMPLING_FREQUENCY;
                try {
                    TimeUnit.SECONDS.sleep(DissertationConstants.CAR_MOVEMENT.SAMPLING_FREQUENCY);
                } catch (InterruptedException e) {
                    LOGGER.error("", e);
                }
            }
        }
        car.setSpeed(DissertationConstants.CAR_MOVEMENT.INITIAL_SPEED);
        LOGGER.info("Way completed");
        this.interrupt();
    }
}
