package com.xitaymin.todolist.dao;

import com.xitaymin.todolist.entity.Task;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface TaskDao {
    Task upsert();

    Collection<Task> findAll();

    boolean deleteById(Long id);
}
