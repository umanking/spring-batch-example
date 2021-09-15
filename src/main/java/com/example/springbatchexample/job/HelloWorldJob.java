package com.example.springbatchexample.job;

import com.example.springbatchexample.ParameterValidator;
import java.util.List;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.CompositeJobParametersValidator;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return this.jobBuilderFactory.get("basicjob")
                .start(step1())
                .validator(validator())
                .build();
    }

    @Bean
    private Step step1() {
        return this.stepBuilderFactory.get("step1")
                .tasklet(helloTasklet(null, null))
                .build();
    }

    public CompositeJobParametersValidator validator() {
        CompositeJobParametersValidator validator = new CompositeJobParametersValidator();
        DefaultJobParametersValidator defaultJobParametersValidator = new DefaultJobParametersValidator(
                new String[]{"fileName"},
                new String[]{"name"}
        );

        // 2개의 valiadtor로 구성
        validator.setValidators(List.of(new ParameterValidator(), defaultJobParametersValidator));

        return validator;
    }

    @StepScope
    public Tasklet helloTasklet(@Value("#{jobParameters['name']}") String name,
            @Value("#{jobParameters['fileName']}") String fileName) {
        return (stepContribution, chunkContext) -> {
            // 1. job parameter 얻어오기
/*            StepContext stepContext = chunkContext.getStepContext();
            Map<String, Object> jobParameters = stepContext.getJobParameters();
            String name = (String) jobParameters.get("name");
            System.out.println("jobParameters = " + jobParameters);*/
            System.out.println("name = " + name + ", fileName = " + fileName);
            return RepeatStatus.FINISHED;
        };
    }

}
