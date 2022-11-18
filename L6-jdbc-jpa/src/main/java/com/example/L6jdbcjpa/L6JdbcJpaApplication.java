package com.example.L6jdbcjpa;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

@SpringBootApplication
public class L6JdbcJpaApplication {


	@Value("${spring.datasource.driver}")
	private String driver;

	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;

	@Bean
	public DataSource h2DataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}
	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource h2DataSource){
		return new NamedParameterJdbcTemplate(h2DataSource);
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource h2DataSource){
		return new JdbcTemplate(h2DataSource);
	}





	@Bean
	public RowMapper<Person> productRowMapper(){
		RowMapper<Person> productRowMapper = new RowMapper<Person>() {
			@Override
			public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
				Person person = new Person();
				person.setId(rs.getInt(1));
				person.setName(rs.getString(2));
				person.setEmail(rs.getString(3));
				person.setPhone(rs.getString(4));

				return person;
			}
		};
		return productRowMapper;
	}
	public static void main(String[] args) {
		SpringApplication.run(L6JdbcJpaApplication.class, args);
	}

}
