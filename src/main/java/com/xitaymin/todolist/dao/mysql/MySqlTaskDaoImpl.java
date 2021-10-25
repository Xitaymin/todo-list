package com.xitaymin.todolist.dao.mysql;

import com.xitaymin.todolist.dao.TaskDao;
import com.xitaymin.todolist.entity.Task;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class MySqlTaskDaoImpl implements TaskDao {
    @Override
    public Task upsert(Task task) {
        return null;
    }

    @Override
    public Collection<Task> findAll() {
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    @Override
    public void deleteAll() {

    }
}
