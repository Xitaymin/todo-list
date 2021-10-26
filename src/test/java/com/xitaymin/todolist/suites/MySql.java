package com.xitaymin.todolist.suites;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.Map;


public class MySql {
    public static final MySQLContainer<?> CONTAINER = new MySQLContainer<>("mysql:8.0.27");

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext context) {
            Startables.deepStart(CONTAINER).join();

            context.getEnvironment()
                    .getPropertySources()
                    .addFirst(new MapPropertySource("testcontainers",
                            Map.of("spring.datasource.url",
                                    CONTAINER.getJdbcUrl(),
                                    "spring.datasource.username",
                                    CONTAINER.getUsername(),
                                    "spring.datasource.password",
                                    CONTAINER.getPassword())));
        }
    }
}
