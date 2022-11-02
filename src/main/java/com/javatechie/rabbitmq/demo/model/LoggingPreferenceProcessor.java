package com.javatechie.rabbitmq.demo.model;

import com.javatechie.rabbitmq.demo.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class LoggingPreferenceProcessor implements ItemProcessor<Person, Person> {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingPreferenceProcessor.class);

	@Override
	public Person process(Person item) throws Exception {
		LOGGER.info("Processing Preference information: {}", item);
		return item;
	}
}
