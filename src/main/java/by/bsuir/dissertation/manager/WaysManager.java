package by.bsuir.dissertation.manager;

import by.bsuir.dissertation.annotation.InjectWaysGenerator;
import by.bsuir.dissertation.repository.WayRepository;
import by.bsuir.dissertation.util.WaysGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WaysManager implements Runnable {

    private final static Logger LOGGER = LoggerFactory.getLogger(WaysManager.class);

    private final WayRepository wayRepository;

    @InjectWaysGenerator
    private List<WaysGenerator> waysGenerators;

    private final TaskExecutor taskExecutor;

    @Autowired
    public WaysManager(WayRepository wayRepository, TaskExecutor waysManagerTaskExecutor) {
        this.wayRepository = wayRepository;
        this.taskExecutor = waysManagerTaskExecutor;
    }

    private void execute() {
        for (WaysGenerator waysGenerator : waysGenerators) {
            taskExecutor.execute(waysGenerator);
        }
    }

    @Override
    public void run() {
        boolean isWork = true;
        LOGGER.info("START CREATING WAYS");
        execute();
        while (isWork) {
            for (int i = 0; i < waysGenerators.size(); i++) {
                WaysGenerator waysGenerator = waysGenerators.get(i);
                if (waysGenerator.isDone()) {
                    wayRepository.save(waysGenerator.getWays());
                    waysGenerators.remove(waysGenerator);
                }
            }
            if (waysGenerators.size() == 0) {
                if (((ThreadPoolTaskExecutor) taskExecutor).getActiveCount() == 0) {
                    ((ThreadPoolTaskExecutor) taskExecutor).shutdown();
                }
                isWork = false;
                LOGGER.info("FINISH CREATING WAYS");
            }
        }
    }
}

