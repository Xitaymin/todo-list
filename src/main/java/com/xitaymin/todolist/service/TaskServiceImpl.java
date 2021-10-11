package com.xitaymin.todolist.service;

import com.xitaymin.todolist.dao.TaskDao;
import com.xitaymin.todolist.entity.Task;

import java.util.Collection;

public class TaskServiceImpl implements TaskService {
    public TaskServiceImpl(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    private final TaskDao taskDao;

    @Override
    public Task saveOrUpdateExisting() {
        return taskDao.upsert();
    }

    @Override
    public Collection<Task> getTasks() {
        return null;
    }

    @Override
    public void deleteTask(Long id) {

    }
}
