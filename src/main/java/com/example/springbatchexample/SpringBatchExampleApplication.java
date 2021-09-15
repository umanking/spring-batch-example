package com.example.springbatchexample;

import java.util.Arrays;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.CompositeJobParametersValidator;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchExampleApplication.class, args);
    }

//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public CompositeJobParametersValidator validator() {
//        CompositeJobParametersValidator validator = new CompositeJobParametersValidator();
//        DefaultJobParametersValidator defaultJobParametersValidator = new DefaultJobParametersValidator(
//                new String[]{"fileName"},
//                new String[]{"name", "foo", "currentDate", "message"}
//        );
//
//        defaultJobParametersValidator.afterPropertiesSet();
//        validator.setValidators(Arrays.asList(new ParameterValidator(), defaultJobParametersValidator));
//        return validator;
//    }
//
//    @Bean
//    public Job job() {
//        return this.jobBuilderFactory.get("basicjob")
//                .start(step())
//                .validator(validator())
//                .incrementer(new DailyJobTimestamper())
//                .listener(new JobLoggerListener())
//                .build();
//    }
//
//    @Bean
//    public Step step() {
//        return this.stepBuilderFactory.get("step1")
//                .tasklet(helloTasklet())
//                .build();
//    }
//
//    // jobparameter 얻어오기 1 - chunck context
//    private Tasklet helloTasklet() {
//        return (stepContribution, chunkContext) -> {
//            String name = (String) chunkContext.getStepContext()
//                    .getJobParameters()
//                    .get("name");
//
//            ExecutionContext jobContext = chunkContext.getStepContext().getStepExecution()
//                    .getJobExecution()
//                    .getExecutionContext();
//
//            jobContext.put("user.name", name);
//
//            System.out.println(String.format("Hello %s", name));
//            return RepeatStatus.FINISHED;
//        };
//    }
//
//
//    // jobparamter 얻어오기 2 - late bind
//    @Bean
//    @StepScope
//    public Tasklet helloTasklet2(
//            @Value(("#{jobParameters['name']}")) String name,
//            @Value(("#{jobParameters['fileName']}")) String fileName) {
//        return (stepContribution, chunkContext) -> {
//            System.out.println(String.format("Hello %s", name));
//            System.out.println(String.format("fileName = %s", fileName));
//            return RepeatStatus.FINISHED;
//        };
//    }

}
