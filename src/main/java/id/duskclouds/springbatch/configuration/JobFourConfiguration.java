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
public class JobFourConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step fourMyStepOne() {
        return stepBuilderFactory.get("fourMyStepOne").tasklet(((contribution, chunkContext) -> {
            System.out.println(String.format("fourMyStepOne was executed executed on thread %s", Thread.currentThread().getName()));
            return RepeatStatus.FINISHED;
        })).build();
    }

    @Bean
    public Step fourMyStepTwo() {
        return stepBuilderFactory.get("fourMyStepTwo").tasklet(((contribution, chunkContext) -> {
            System.out.println(String.format("fourMyStepTwo was executed executed on thread %s", Thread.currentThread().getName()));
            return RepeatStatus.FINISHED;
        })).build();
    }

    @Bean
    public Job jobFour() {
        return jobBuilderFactory.get("jobFour")
            .incrementer(new RunIdIncrementer())
            .start(fourMyStepOne())
            .next(fourMyStepTwo())
            .build();
    }
}
