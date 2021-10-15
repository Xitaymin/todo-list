package com.xitaymin.todolist.service;

import com.xitaymin.todolist.dao.TaskDao;
import com.xitaymin.todolist.entity.Task;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
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
        return taskDao.findAll();
    }

    @Override
    public void deleteTask(Long id) {
        taskDao.deleteById(id);
    }
}
