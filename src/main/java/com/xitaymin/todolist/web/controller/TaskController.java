package com.xitaymin.todolist.web.controller;

import com.xitaymin.todolist.entity.Task;
import com.xitaymin.todolist.model.service.TaskService;
import org.springframework.web.bind.annotation.*;

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

