package com.xitaymin.todolist.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("postgres")
@ComponentScan("com.xitaymin.todolist.dao.postgres")
public class PostgresDaoConfiguration {
}
