package com.example.springbatchexample.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FlowJob {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return this.jobBuilderFactory.get("codntionalStepLogicJob")
                .start(preProcessingFlow())
                .next(runBatch())
                .end()
                .build();
    }

    @Bean
    private Step runBatch() {
        return this.stepBuilderFactory.get("runBatch")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("runBatch tasklet !");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Flow preProcessingFlow() {
        return new FlowBuilder<Flow>("preProcessingFlow")
                .start(loadFileStep())
                .next(loadCustomerStep())
                .next(updateStartStep())
                .build();
    }

    @Bean
    private Step loadFileStep() {
        return this.stepBuilderFactory.get("loadFileStep")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("loadFileStep tasklet !");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    private Step loadCustomerStep() {
        return this.stepBuilderFactory.get("loadCustomerStep")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("loadCustomer tasklet !");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    private Step updateStartStep() {
        return this.stepBuilderFactory.get("updateStartStep")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("updateStart tasklet !");
                    return RepeatStatus.FINISHED;
                }).build();
    }

}
