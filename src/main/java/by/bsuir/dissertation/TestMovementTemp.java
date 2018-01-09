package by.bsuir.dissertation;

import by.bsuir.dissertation.entity.general.Car;
import by.bsuir.dissertation.entity.PartWay;
import by.bsuir.dissertation.entity.Way;
import by.bsuir.dissertation.manager.TrafficManager;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestMovementTemp {

    public static void main(String[] args) throws InterruptedException {
        // first way
        Map<Integer, PartWay> mapWay1 = new HashMap<>();
        PartWay partWay11 = new PartWay();
        partWay11.setDistance(100);
        PartWay partWay12 = new PartWay();
        partWay12.setDistance(50);
        PartWay partWay13 = new PartWay();
        partWay13.setDistance(80);
        mapWay1.put(0, partWay11);
        mapWay1.put(2, partWay13);
        mapWay1.put(1, partWay12);
        Way way1 = new Way("1", "firstWay", 3, mapWay1);

        // second way
        Map<Integer, PartWay> mapWay2 = new HashMap<>();
        PartWay partWay21 = new PartWay();
        partWay21.setDistance(45);
        PartWay partWay22 = new PartWay();
        partWay22.setDistance(120);
        PartWay partWay23 = new PartWay();
        partWay23.setDistance(90);
        mapWay2.put(0, partWay21);
        mapWay2.put(1, partWay22);
        mapWay2.put(2, partWay23);
        Way way2 = new Way("2", "secondWay", 3, mapWay2);

        // start cars
        TrafficManager trafficManager1 = new TrafficManager(new Car(), way1);
        TimeUnit.MILLISECONDS.sleep(1000);
        TrafficManager trafficManager2 = new TrafficManager(new Car(), way2);
    }
}
