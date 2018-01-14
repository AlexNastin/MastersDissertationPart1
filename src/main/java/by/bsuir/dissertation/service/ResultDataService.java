package by.bsuir.dissertation.service;

import by.bsuir.dissertation.entity.Car;
import by.bsuir.dissertation.entity.PartResultData;

import java.util.List;

public interface ResultDataService {

    void saveResult(Car car, List<PartResultData> partResultData);
}
