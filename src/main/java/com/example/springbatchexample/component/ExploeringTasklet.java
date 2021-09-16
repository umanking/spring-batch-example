package com.example.springbatchexample.component;

import java.util.List;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class ExploeringTasklet implements Tasklet {

    private JobExplorer jobExplorer;

    public ExploeringTasklet(JobExplorer jobExplorer) {
        this.jobExplorer = jobExplorer;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        String jobName = chunkContext.getStepContext().getJobName();

        List<JobInstance> jobInstances = jobExplorer.getJobInstances(jobName, 0, Integer.MAX_VALUE);

        System.out.println("jobName = " + jobName);
        System.out.println("jobInstances size:" + jobInstances.size());

        for (JobInstance jobInstance : jobInstances) {
            List<JobExecution> jobExecutions = this.jobExplorer.getJobExecutions(jobInstance);
            long instanceId = jobInstance.getInstanceId();
            System.out.println("instanceId = " + instanceId);
            System.out.println("jobExecutions size = " + jobExecutions.size());

            for (JobExecution jobExecution : jobExecutions) {
                Long id = jobExecution.getId();
                ExitStatus exitStatus = jobExecution.getExitStatus();
                System.out.println("id = " + id);
                System.out.println("exitStatus = " + exitStatus);

            }
        }
        return RepeatStatus.FINISHED;
    }

}
