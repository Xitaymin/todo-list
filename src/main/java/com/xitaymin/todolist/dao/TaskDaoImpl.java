package com.xitaymin.todolist.dao;

import com.xitaymin.todolist.entity.Task;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class TaskDaoImpl implements TaskDao {
    @Override
    public Task upsert() {
        return null;
    }

    @Override
    public Collection<Task> findAll() {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}
