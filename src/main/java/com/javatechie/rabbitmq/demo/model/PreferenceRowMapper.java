package com.javatechie.rabbitmq.demo.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PreferenceRowMapper implements RowMapper<Person> {

	@Override
	public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
		Person person = new Person(rs.getInt("id"), rs.getString("name"));
		
		return person;
	}

}
