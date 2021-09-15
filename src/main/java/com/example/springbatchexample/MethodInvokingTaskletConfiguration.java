//package com.example.springbatchexample;
//
//import com.example.springbatchexample.service.CustomService;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
////@EnableBatchProcessing
//@Component
//public class MethodInvokingTaskletConfiguration {
//
//    @Autowired
//    JobBuilderFactory jobBuilderFactory;
//    @Autowired
//    StepBuilderFactory stepBuilderFactory;
//    @Autowired
//    CustomService customService;
//
//    @Bean
//    public Job methodInvokingJob() {
//        return this.jobBuilderFactory.get("methodInvokingJob")
//                .start(methodInvokingStep())
//                .build();
//    }
//
//    private Step methodInvokingStep() {
//        return stepBuilderFactory.get("methodInvokingStep")
//                .tasklet(methodInvokingTasklet(null))
//                .build();
//    }
//
//    private Tasklet methodInvokingTasklet(@Value("#{jobParameters['message']}") String messaege) {
//        MethodInvokingTaskletAdapter methodInvokingTaskletAdapter = new MethodInvokingTaskletAdapter();
//        methodInvokingTaskletAdapter.setTargetObject(customService);
//        methodInvokingTaskletAdapter.setTargetMethod("serviceMethod");
//        methodInvokingTaskletAdapter.setArguments(new String[]{messaege});
//        return methodInvokingTaskletAdapter;
//    }
//}
