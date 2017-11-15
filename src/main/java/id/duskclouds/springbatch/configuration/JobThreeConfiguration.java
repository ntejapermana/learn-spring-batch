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
public class JobThreeConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step threeMyStepOne() {
        return stepBuilderFactory.get("threeMyStepOne").tasklet(((contribution, chunkContext) -> {
            System.out.println(String.format("threeMyStepOne was executed executed on thread %s", Thread.currentThread().getName()));
            return RepeatStatus.FINISHED;
        })).build();
    }

    @Bean
    public Step threeMyStepTwo() {
        return stepBuilderFactory.get("threeMyStepTwo").tasklet(((contribution, chunkContext) -> {
            System.out.println(String.format("threeMyStepTwo was executed executed on thread %s", Thread.currentThread().getName()));
            return RepeatStatus.FINISHED;
        })).build();
    }

    @Bean
    public Job jobThree() {
        return jobBuilderFactory.get("jobThree")
            .incrementer(new RunIdIncrementer())
            .start(threeMyStepOne())
            .next(threeMyStepTwo())
            .build();
    }
}
