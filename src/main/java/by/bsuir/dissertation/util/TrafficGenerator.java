package by.bsuir.dissertation.util;

import by.bsuir.dissertation.entity.Car;
import by.bsuir.dissertation.entity.PartResultData;
import by.bsuir.dissertation.entity.PartWay;
import by.bsuir.dissertation.service.NodeService;
import by.bsuir.dissertation.service.ResultDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@Scope("prototype")
public class TrafficGenerator implements Runnable {

    private final static Logger LOGGER = LoggerFactory.getLogger(TrafficGenerator.class);

    private final ResultDataService resultDataService;
    private final NodeService nodeService;

    private Car car;
    private Map<Integer, PartWay> way;

    private boolean isDone;

    @Autowired
    public TrafficGenerator(ResultDataService resultDataService, NodeService nodeService) {
        this.resultDataService = resultDataService;
        this.nodeService = nodeService;
    }

    @Override
    public void run() {
        boolean isMaxSpeed = false;
        double speed = car.getSpeed();
        List<PartResultData> partResultData = new ArrayList<>();
        for (int i = 0; i < way.size(); i++) {
            PartWay partWay = way.get(i);
            if (partWay.isCamera()) {
                partResultData.add(new PartResultData(new Date(), nodeService.getNode(partWay.getId())));
            }
            double currentDistance = DissertationConstants.CAR_MOVEMENT.INITIAL_DISTANCE;
            while (currentDistance < partWay.getDistance()) {
                LOGGER.info("Current distance: " + currentDistance);
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
        resultDataService.saveResult(car, partResultData);
        LOGGER.info("Way completed");
        this.isDone = true;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setWay(Map<Integer, PartWay> way) {
        this.way = way;
    }
}
