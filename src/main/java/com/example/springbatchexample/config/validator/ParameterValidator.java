package com.example.springbatchexample.config.validator;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.util.StringUtils;

public class ParameterValidator implements JobParametersValidator {

    @Override
    public void validate(JobParameters jobParameters) throws JobParametersInvalidException {
        String fileName = jobParameters.getString("fileName");
        if (!StringUtils.hasText(fileName)) {
            // 빈값이면 예외
            throw new JobParametersInvalidException("filename parameter is mission");
        } else if (!StringUtils.endsWithIgnoreCase(fileName, "csv")) {
            // csv로 끝나지 않으면 예외
            throw new JobParametersInvalidException("file name parameter does not use the csv file extension");
        }
    }
}
