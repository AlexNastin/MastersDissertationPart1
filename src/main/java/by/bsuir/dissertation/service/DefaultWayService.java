package by.bsuir.dissertation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.bsuir.dissertation.entity.Way;
import by.bsuir.dissertation.repository.WayRepository;

@Service
public class DefaultWayService implements WayService {

    private final WayRepository wayRepository;

    @Autowired
    public DefaultWayService(WayRepository wayRepository) {
        this.wayRepository = wayRepository;
    }

    @Override
    public List<Way> getWays() {
        return wayRepository.findAll();
    }
}
