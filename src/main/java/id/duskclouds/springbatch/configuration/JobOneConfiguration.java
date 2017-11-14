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
public class JobOneConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step oneMyStepOne() {
        return stepBuilderFactory.get("oneMyStepOne").tasklet(((contribution, chunkContext) -> {
            System.out.println("oneMyStepOne was executed");
            return RepeatStatus.FINISHED;
        })).build();
    }

    @Bean
    public Step oneMyStepTwo() {
        return stepBuilderFactory.get("oneMyStepTwo").tasklet(((contribution, chunkContext) -> {
            System.out.println("oneMyStepTwo was executed");
            return RepeatStatus.FINISHED;
        })).build();
    }

    @Bean
    public Job jobOne() {
        return jobBuilderFactory.get("jobOne")
            .incrementer(new RunIdIncrementer())
            .start(oneMyStepOne())
            .next(oneMyStepTwo())
            .build();
    }
}
