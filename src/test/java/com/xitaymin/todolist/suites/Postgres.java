package com.xitaymin.todolist.suites;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.Map;

public class Postgres {

    private static final PostgreSQLContainer<?> container =
            new PostgreSQLContainer<>("postgres:13.4").withInitScript("schema-postgres.sql");


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
