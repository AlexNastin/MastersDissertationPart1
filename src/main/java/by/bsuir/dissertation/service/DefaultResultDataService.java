package by.bsuir.dissertation.service;

import by.bsuir.dissertation.entity.Car;
import by.bsuir.dissertation.entity.PartResultData;
import by.bsuir.dissertation.entity.ResultData;
import by.bsuir.dissertation.repository.PartResultDataRepository;
import by.bsuir.dissertation.repository.ResultDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultResultDataService implements ResultDataService {

    private final ResultDataRepository resultDataRepository;
    private final PartResultDataRepository partResultDataRepository;

    @Autowired
    public DefaultResultDataService(ResultDataRepository resultDataRepository, PartResultDataRepository partResultDataRepository) {
        this.resultDataRepository = resultDataRepository;
        this.partResultDataRepository = partResultDataRepository;
    }

    @Override
    public void saveResult(Car car, List<PartResultData> partResultData) {
        partResultData = partResultDataRepository.save(partResultData);
        resultDataRepository.save(new ResultData(car, partResultData));
    }
}
