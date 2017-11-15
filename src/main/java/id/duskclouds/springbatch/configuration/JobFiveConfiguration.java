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
public class JobFiveConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step fiveMyStepOne() {
        return stepBuilderFactory.get("fiveMyStepOne").tasklet(((contribution, chunkContext) -> {
            System.out.println(String.format("fiveMyStepOne was executed executed on thread %s", Thread.currentThread().getName()));
            return RepeatStatus.FINISHED;
        })).build();
    }

    @Bean
    public Step fiveMyStepTwo() {
        return stepBuilderFactory.get("fiveMyStepTwo").tasklet(((contribution, chunkContext) -> {
            System.out.println(String.format("fiveMyStepTwo was executed executed on thread %s", Thread.currentThread().getName()));
            return RepeatStatus.FINISHED;
        })).build();
    }

    @Bean
    public Job jobFive() {
        return jobBuilderFactory.get("jobFive")
            .incrementer(new RunIdIncrementer())
            .start(fiveMyStepOne())
            .next(fiveMyStepTwo())
            .build();
    }
}
