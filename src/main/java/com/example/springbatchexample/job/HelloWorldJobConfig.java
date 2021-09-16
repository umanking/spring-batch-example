package com.example.springbatchexample.job;

import com.example.springbatchexample.component.incrementer.DailyJobTimeStamper;
import com.example.springbatchexample.component.listener.JobLoggerListener;
import com.example.springbatchexample.component.validator.ParameterValidator;
import java.util.List;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.CompositeJobParametersValidator;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//@Component
public class HelloWorldJobConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return this.jobBuilderFactory.get("basicJob")
                .start(step1())
                .validator(validator())
                .incrementer(new DailyJobTimeStamper())
                .listener(new JobLoggerListener())
                .build();
    }

    @Bean
    public Step step1() {
        return this.stepBuilderFactory.get("step1")
                .tasklet(helloTask())
                .build();
    }

    @Bean
    public Tasklet helloTask() {
        return new HelloWorld();
    }

    public static class HelloWorld implements Tasklet{

        @Override
        public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
            String name =(String) chunkContext.getStepContext().getJobParameters().get("name");

            ExecutionContext executionContext = chunkContext.getStepContext()
                    .getStepExecution()
                    .getExecutionContext();

            executionContext.put("user.name", name);
            System.out.println("name = " + name);

            return RepeatStatus.FINISHED;
        }
    }

    public CompositeJobParametersValidator validator() {
        CompositeJobParametersValidator validator = new CompositeJobParametersValidator();
        DefaultJobParametersValidator defaultJobParametersValidator = new DefaultJobParametersValidator(
                new String[]{"fileName"},
                new String[]{"name", "currentDate", "run.id", "message"}
        );

        // overlap되는걸 방지?
        defaultJobParametersValidator.afterPropertiesSet();
        validator.setValidators(List.of(new ParameterValidator(), defaultJobParametersValidator));
        return validator;
    }

    @Bean
    @StepScope
    public Tasklet helloTasklet(
            @Value("#{jobParameters['name']}") String name,
            @Value("#{jobParameters['fileName']}") String fileName) {
        return (stepContribution, chunkContext) -> {

            // chunk > step > job > job의 executionContext
            ExecutionContext jobExecutionContext =
                    chunkContext.getStepContext()
                            .getStepExecution()
                            .getExecutionContext();

            jobExecutionContext.put("name", "andrew");

            System.out.println(String.format("Hello, %s", name));
            System.out.println("name = " + name + ", fileName = " + fileName);
            return RepeatStatus.FINISHED;
        };
    }
}
