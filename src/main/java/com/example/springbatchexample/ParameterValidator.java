package com.example.springbatchexample;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.util.StringUtils;

public class ParameterValidator implements JobParametersValidator {

    @Override
    public void validate(JobParameters jobParameters) throws JobParametersInvalidException {
        // TODO: 2021/07/25 validate jobparameter
        String fileName = jobParameters.getString("fileName");
        if (!StringUtils.hasText(fileName)) {
            throw new JobParametersInvalidException("filename parameter is mission");
        } else if (!StringUtils.endsWithIgnoreCase(fileName, "csv")) {
            throw new JobParametersInvalidException("file name paramter does not use the csv file extention");
        }

    }
}
