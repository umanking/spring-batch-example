package com.example.springbatchexample.job;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ChunkJobConfig {

    @Autowired
    JobBuilderFactory jobBuilderFactory;
    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return jobBuilderFactory.get("chunkJob")
                .start(chunkStep())
                .build();
    }

    @Bean
    private Step chunkStep() {
        return stepBuilderFactory.get("chunkStep")
                .<String, String>chunk(100)
                .reader(itemReader())
                .writer(itemWriter())
                .build();

    }

    @Bean
    private ListItemReader<String> itemReader() {
        List<String> items = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            items.add(UUID.randomUUID().toString());
        }
        return new ListItemReader<>(items);
    }

    @Bean
    private ItemWriter<String> itemWriter() {
        return items -> {
            for (String item : items) {
                System.out.println("current item = " + item);
            }
        };
    }

}
