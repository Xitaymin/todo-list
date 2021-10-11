package com.xitaymin.todolist.service;

import com.xitaymin.todolist.entity.Task;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface TaskService {
    Task saveOrUpdateExisting();

    Collection<Task> getTasks();

    void deleteTask(Long id);
}
