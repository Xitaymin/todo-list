package com.xitaymin.todolist.web.controller;

import com.xitaymin.todolist.entity.Task;
import com.xitaymin.todolist.service.TaskService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("todo")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task createTask(@Valid @RequestBody Task task){return taskService.saveOrUpdateExisting(task);}

    @GetMapping
    public Collection<Task> getTasks(){return taskService.getTasks();
    }

    @DeleteMapping()
    public void deleteTask(@RequestParam(required = false) Integer id) {
        taskService.deleteTask(id);
    }

}

