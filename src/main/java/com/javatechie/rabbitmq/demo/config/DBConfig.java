package com.javatechie.rabbitmq.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DBConfig {

	private static final Logger LOG = LoggerFactory.getLogger(DBConfig.class);

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("**");
		ds.setUrl("*");
		ds.setUsername("sa");
		ds.setPassword("*");

		LOG.info("******* DATABASE INITIALIZED SUCCESSFULLY *********");
		return ds;

	}
}
