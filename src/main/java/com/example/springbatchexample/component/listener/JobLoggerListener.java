package com.example.springbatchexample.component.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class JobLoggerListener implements JobExecutionListener {

    private static String START_MESSAGE = "%s is beginning execution";
    private static String END_MESSAGE = "%s ha completed with the status %s";

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println(">>>> beforeJob");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        // job의 성공/실패와 상관없이 무조건 실행됨
        System.out.println(">>>> afterJob");
    }
}
