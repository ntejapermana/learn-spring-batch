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

import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class JobOneConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step oneMyStepOne() {
        return stepBuilderFactory.get("oneMyStepOne").tasklet(((contribution, chunkContext) -> {
            SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
            System.out.println(String.format("oneMyStepOne was executed executed on thread %s at %s", Thread.currentThread().getName(), formatter.format(new Date())));
            return RepeatStatus.FINISHED;
        })).build();
    }

    @Bean
    public Step oneMyStepTwo() {
        return stepBuilderFactory.get("oneMyStepTwo").tasklet(((contribution, chunkContext) -> {
            SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
            System.out.println(String.format("oneMyStepTwo was executed executed on thread %s at %s", Thread.currentThread().getName(), formatter.format(new Date())));
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
