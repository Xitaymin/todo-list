package com.xitaymin.todolist.suites;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.Map;


public class MySql {
    private static final MySQLContainer<?> container =
            new MySQLContainer<>("mysql:8.0.27").withInitScript("schema-mysql.sql");

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext context) {
            Startables.deepStart(container).join();

            context.getEnvironment()
                    .getPropertySources()
                    .addFirst(new MapPropertySource("testcontainers",
                            Map.of("spring.datasource.url",
                                    container.getJdbcUrl(),
                                    "spring.datasource.username",
                                    container.getUsername(),
                                    "spring.datasource.password",
                                    container.getPassword())));
        }
    }
}
