package com.example.springbatchexample.web;

import java.util.Properties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

@Getter
@Setter
public class JobLauncherRequest {

    private String name;
    private Properties jobParameters;

    public Properties getJobParametersProperties() {
        return jobParameters;
    }

    public JobParameters getJobParameters() {
        Properties properties = new Properties();
        properties.putAll(this.jobParameters);
        return new JobParametersBuilder(properties)
                .toJobParameters();
    }
}
