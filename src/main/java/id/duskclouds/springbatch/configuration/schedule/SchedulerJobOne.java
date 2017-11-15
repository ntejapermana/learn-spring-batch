package id.duskclouds.springbatch.configuration.schedule;

import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Profile({"jobOne", "all"})
public class SchedulerJobOne {

    @Autowired
    public JobOperator jobOperator;

    @Scheduled(fixedDelay = 5000l)
    public void runJobOne() throws Exception {
        this.jobOperator.startNextInstance("jobOne");
    }
}
