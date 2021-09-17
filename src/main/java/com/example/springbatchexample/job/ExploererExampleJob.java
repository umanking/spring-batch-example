package com.example.springbatchexample.job;

import com.example.springbatchexample.component.ExploeringTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

//@Component
public class ExploererExampleJob {

    @Autowired
    JobBuilderFactory jobBuilderFactory;
    @Autowired
    StepBuilderFactory stepBuilderFactory;
    @Autowired
    JobExplorer jobExplorer;

    @Bean
    public Job explorerJob() {
        return this.jobBuilderFactory.get("explorerJob")
                .start(explorerStep())
                .build();
    }

    @Bean
    private Step explorerStep() {
        return this.stepBuilderFactory.get("explorerStep")
                .tasklet(explorerTasklet())
                .build();
    }

    @Bean
    private Tasklet explorerTasklet() {
        return new ExploeringTasklet(this.jobExplorer);
    }

}
