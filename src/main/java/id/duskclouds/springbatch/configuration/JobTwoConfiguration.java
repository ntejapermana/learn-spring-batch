package id.duskclouds.springbatch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobTwoConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step twoMyStepOne() {
        return stepBuilderFactory.get("twoMyStepOne").tasklet(((contribution, chunkContext) -> {
            System.out.println("twoMyStepOne was executed");
            return RepeatStatus.FINISHED;
        })).build();
    }

    @Bean
    public Step twoMyStepTwo() {
        return stepBuilderFactory.get("twoMyStepTwo").tasklet(((contribution, chunkContext) -> {
            System.out.println("twoMyStepTwo was executed");
            return RepeatStatus.FINISHED;
        })).build();
    }

    @Bean
    public Job jobTwo() {
        return jobBuilderFactory.get("jobTwo")
            .incrementer(new RunIdIncrementer())
            .start(twoMyStepOne())
            .next(twoMyStepTwo())
            .build();
    }
}
