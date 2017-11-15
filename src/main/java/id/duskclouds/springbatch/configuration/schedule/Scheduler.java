package id.duskclouds.springbatch.configuration.schedule;

import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Profile("all")
public class Scheduler {

    @Autowired
    public JobOperator jobOperator;

    @Scheduled(fixedDelay = 12000l)
    public void runJobThree() throws Exception {
        this.jobOperator.startNextInstance("jobThree");
    }

    @Scheduled(fixedDelay = 17000l)
    public void runJobFour() throws Exception {
        this.jobOperator.startNextInstance("jobFour");
    }
}
