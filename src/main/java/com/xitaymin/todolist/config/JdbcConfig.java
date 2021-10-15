package com.xitaymin.todolist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig {
    @Bean
    DataSource dataSource(){
        DriverManagerDataSource dataSource = new SingleConnectionDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/todo_list");
        dataSource.setUsername("root");
        dataSource.setPassword("JA6k9Zw");
        return dataSource;
    }
}
