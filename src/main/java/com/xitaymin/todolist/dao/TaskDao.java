package com.xitaymin.todolist.dao;

import com.xitaymin.todolist.entity.Task;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDao {
    Task upsert();
}
