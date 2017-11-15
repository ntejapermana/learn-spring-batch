package id.duskclouds.springbatch.configuration.schedule;

import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Profile({"jobTwo", "all"})
public class SchedulerJobTwo {

    @Autowired
    public JobOperator jobOperator;

    @Scheduled(fixedDelay = 8000l)
    public void runJobTwo() throws Exception {
        this.jobOperator.startNextInstance("jobTwo");
    }
}
