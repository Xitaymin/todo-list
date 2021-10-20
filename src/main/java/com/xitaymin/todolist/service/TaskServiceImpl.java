package com.xitaymin.todolist.service;

import com.xitaymin.todolist.dao.TaskDao;
import com.xitaymin.todolist.entity.Task;
import com.xitaymin.todolist.service.exceptions.NotFoundResourceException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TaskServiceImpl implements TaskService {

    public static final String TASK_NOT_FOUND = "Task with id = %s wasn't found.";

    public TaskServiceImpl(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    private final TaskDao taskDao;

    @Override
    public Task saveOrUpdateExisting(Task task) {
        return taskDao.upsert(task);
    }

    @Override
    public Collection<Task> getTasks() {
        return taskDao.findAll();
    }

    @Override
    public void deleteTask(Integer id) {
        if (id == null) {
            taskDao.deleteAll();
            return;
        }
        boolean deleted = taskDao.deleteById(id);
        if (!deleted) {
            throw new NotFoundResourceException(String.format(TASK_NOT_FOUND, id));
        }
    }
}
