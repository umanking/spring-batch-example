package com.example.springbatchexample;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class JobLoggerListener implements JobExecutionListener {

    private static String START_MESSAGE = "%s is beginning execution";
    private static String END_MESSAGE = "%s ha completed with the status %s";

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("before job>>>>>> ");

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("after job>>>>>>>>>");
        System.out.println(String.format(END_MESSAGE, jobExecution.getJobInstance().getJobName(), jobExecution.getStatus()));

    }
}
