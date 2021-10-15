package com.xitaymin.todolist.service;

import com.xitaymin.todolist.entity.Task;

import java.util.Collection;

public interface TaskService {
    Task saveOrUpdateExisting(Task task);

    Collection<Task> getTasks();

    void deleteTask(Integer id);
}
