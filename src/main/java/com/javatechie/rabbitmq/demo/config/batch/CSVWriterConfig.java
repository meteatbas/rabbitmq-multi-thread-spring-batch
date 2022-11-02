package com.javatechie.rabbitmq.demo.config.batch;

import com.javatechie.rabbitmq.demo.model.Person;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;


@Configuration
public class CSVWriterConfig {
	
	@Value("${csv.out.path}")
	private String csvOutPath;

	@Bean
	public FlatFileItemWriter<Person> writer() {

		FlatFileItemWriter<Person> writer = new FlatFileItemWriter<Person>();
		writer.setResource(new FileSystemResource(csvOutPath + Instant.now().getEpochSecond() + ".csv"));
		writer.setLineAggregator(new DelimitedLineAggregator<Person>() {
			{
				setDelimiter(",");
				setFieldExtractor(new BeanWrapperFieldExtractor<Person>() {
					{
						setNames(new String[] { "id", "name","name","name", "name","name","name", "name","name","name", "name","name","name", "name","name","name","name","name", "name","name","name", "name","name","name", "name","name","name" , "name","name","name", "name","name","name", "name","name","name", "name","name","name" , "name","name","name", "name","name","name", "name","name","name" , "name","name","name" , "name","name","name", "name","name","name", "name","name","name" });
					}
				});
			}
		});

		return writer;
	}
}
