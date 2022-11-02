package com.javatechie.rabbitmq.demo.config.batch;

import com.javatechie.rabbitmq.demo.model.LoggingPreferenceProcessor;
import com.javatechie.rabbitmq.demo.model.Person;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessorConfig {

	@Bean
    ItemProcessor<Person, Person> processor() {
		 return new LoggingPreferenceProcessor();
	}
}
