package com.even.springbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class JobConfiguration {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job helloWorld () {
        return jobBuilderFactory.get("helloWord").start(JobStep1()).next(JobStep2()).build();
    }

    @Bean
    public Step JobStep1 () {
        return stepBuilderFactory.get("step1").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute (StepContribution stepContribution, ChunkContext chunkContext)
                    throws Exception {
                System.out.println("Hello ");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    @Bean
    public Step JobStep2 () {
        return stepBuilderFactory.get("step2").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute (StepContribution stepContribution, ChunkContext chunkContext)
                    throws Exception {
                System.out.println("World");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }
}
