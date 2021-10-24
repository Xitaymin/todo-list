package com.xitaymin.todolist.suites;

import com.xitaymin.todolist.TodoListApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(TodoListApplication.class)
public class TestSuiteConfiguration {

}
