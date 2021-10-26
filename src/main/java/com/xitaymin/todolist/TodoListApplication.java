package com.xitaymin.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.xitaymin.todolist.config",
                                           "com.xitaymin.todolist.web.controller",
                                           "com.xitaymin.todolist.service"})
public class TodoListApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoListApplication.class, args);
    }
}
