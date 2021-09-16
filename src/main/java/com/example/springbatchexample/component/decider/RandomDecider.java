package com.example.springbatchexample.component.decider;

import java.util.Random;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

public class RandomDecider implements JobExecutionDecider {

    private Random random = new Random();

    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        if (random.nextBoolean()) {
            System.out.println("COMPLETE");
            return new FlowExecutionStatus(FlowExecutionStatus.COMPLETED.getName());
        } else {
            System.out.println("FAILED");
            return new FlowExecutionStatus(FlowExecutionStatus.FAILED.getName());
        }
    }
}
