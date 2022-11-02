package com.javatechie.rabbitmq.demo.config.batch;

import com.javatechie.rabbitmq.demo.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfig {

    private static final Logger LOG = LoggerFactory.getLogger(BatchConfig.class);

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private ItemReader<Person> reader;

    @Autowired
    private ItemWriter<Person> writer;

    @Autowired
    private ItemProcessor<Person, Person> processor;


    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @Bean
    public Step step() {
        Step step = stepBuilderFactory
                .get("DB_TO_CSV_STEP")
                .<Person, Person>chunk(50)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .taskExecutor(jobTaskExecutorMultiThreaded())
                .build();

        LOG.info("********* BATCH STEP CREATED SUCCESSFULLY :{} ************", step);
        return step;
    }

    @Bean
    Job databaseToCsvFileJob(JobBuilderFactory jobBuilderFactory, Step step) {
        Job job = jobBuilderFactory
                .get("DB_TO_CSV_JOB")
                .incrementer(new RunIdIncrementer())
                .flow(step)
                .end()
                .build();

        LOG.info("********* BATCH JOB CREATED SUCCESSFULLY : {} ************", job);
        return job;
    }

//    @Scheduled(cron = "${scheduler.cron}")
//    public void perform() throws Exception {
//        LOG.info("*****************************************************");
//        LOG.info("STARTING SCHEDULER");
//        LOG.info("*****************************************************");
//        Instant startTime = Instant.now();
//
//        JobParameters params = new JobParametersBuilder().addString("JobID", String.valueOf(System.currentTimeMillis()))
//                .toJobParameters();
////		jobLauncher12().run(job,params);
//        jobLauncher.run(job, params);
//
//        Instant finishTime = Instant.now();
//
//        LOG.info("*****************************************************");
//        LOG.info("ENDING SCHEDULER");
//        LOG.info("*****************************************************");
//        LOG.info("***** SCHEDULER COMPLETED JOB in {} ******", Duration.between(startTime, finishTime));
//    }

    @Bean
    public TaskExecutor jobTaskExecutorMultiThreaded() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // there are 21 sites currently hence we have 21 threads
        taskExecutor.setMaxPoolSize(5);
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setQueueCapacity(20);
        taskExecutor.setThreadGroupName("multi-");
        taskExecutor.setThreadNamePrefix("multi-");
        taskExecutor.afterPropertiesSet();
        return taskExecutor;
    }

    @Bean
    public SimpleAsyncTaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor simpleAsyncTaskExecutor = new SimpleAsyncTaskExecutor();
        simpleAsyncTaskExecutor.setConcurrencyLimit(10);
        simpleAsyncTaskExecutor.setThreadPriority(0);
        simpleAsyncTaskExecutor.setThreadNamePrefix("MySimpleAsyncThreads");
        return simpleAsyncTaskExecutor;
    }

}
