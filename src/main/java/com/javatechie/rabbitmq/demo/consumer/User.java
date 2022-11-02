package com.javatechie.rabbitmq.demo.consumer;

import com.javatechie.rabbitmq.demo.config.MessagingConfig;
import com.javatechie.rabbitmq.demo.config.batch.BatchConfig;
import com.javatechie.rabbitmq.demo.dto.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
public class User {
    private static final Logger LOG = LoggerFactory.getLogger(User.class);

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(OrderStatus orderStatus) {
        try {
            this.perform();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        System.out.println("Message recieved from queue : " + orderStatus);
    }

    public void perform() throws Exception {
        LOG.info("*****************************************************");
        LOG.info("STARTING SCHEDULER");
        LOG.info("*****************************************************");
        Instant startTime = Instant.now();

        JobParameters params = new JobParametersBuilder().addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
//		jobLauncher12().run(job,params);
        jobLauncher.run(job, params);

        Instant finishTime = Instant.now();

        LOG.info("*****************************************************");
        LOG.info("ENDING SCHEDULER");
        LOG.info("*****************************************************");
        LOG.info("***** SCHEDULER COMPLETED JOB in {} ******", Duration.between(startTime, finishTime));
    }
}
