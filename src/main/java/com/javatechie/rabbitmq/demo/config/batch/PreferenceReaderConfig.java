package com.javatechie.rabbitmq.demo.config.batch;

import com.javatechie.rabbitmq.demo.model.Person;
import com.javatechie.rabbitmq.demo.model.PreferenceRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.SqlServerPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class PreferenceReaderConfig {

	private static final Logger LOG = LoggerFactory.getLogger(PreferenceReaderConfig.class);

	@Autowired
	private DataSource ds;

	@Bean
	public JdbcPagingItemReader<Person> reader() {
		JdbcPagingItemReader<Person> reader = new JdbcPagingItemReader<Person>();

//		reader.setSql("SELECT * FROM springBatchUser");
		reader.setRowMapper(new PreferenceRowMapper());

		LOG.info("********* PREFERENCE READER CONFIGURED SUCCESSFULLY - {} *************", reader);
		SqlServerPagingQueryProvider queryProvider = new SqlServerPagingQueryProvider();
		queryProvider.setSelectClause("*");
		queryProvider.setFromClause("springBatchUserTwo");
		Map<String, Order> sortKeys = new HashMap<>(2);
		sortKeys.put("id", Order.ASCENDING);
		queryProvider.setSortKeys(sortKeys);
		reader.setDataSource(ds);
		reader.setPageSize(100);
		reader.setFetchSize(100);
		reader.setSaveState(false);
		reader.setQueryProvider(queryProvider);
		return reader;
	}
}
