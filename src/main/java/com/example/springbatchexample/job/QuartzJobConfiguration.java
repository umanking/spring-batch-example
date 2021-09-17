package com.example.springbatchexample.job;

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
public class QuartzJobConfiguration {

    @Autowired
    JobBuilderFactory jobBuilderFactory;
    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job quartzJob() {
        return jobBuilderFactory.get("quartzJob")
                .incrementer(new RunIdIncrementer())
                .start(quartzStep1())
                .build();
    }

    @Bean
    public Step quartzStep1() {
        return stepBuilderFactory.get("quartzStep1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("run step1 - QuartzJob");
                    return RepeatStatus.FINISHED;
                }).build();
    }

}
