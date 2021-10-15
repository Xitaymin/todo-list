package com.xitaymin.todolist.dao;

import com.xitaymin.todolist.entity.Task;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

@Repository
public class TaskDaoImpl implements TaskDao {
    private static final RowMapper<Task> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Task.class);
    private final JdbcTemplate jdbcTemplate;

    public TaskDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Task upsert(Task task) {

        return null;
    }

    @Override
    public Collection<Task> findAll() {
        //todo format case
        return jdbcTemplate.query("SELECT * FROM tasks order by id", (rs, rowNum) -> {
            Task task = new Task();
            task.setId(rs.getInt("id"));
            task.setDescription(rs.getString("description"));
            return task;
        });

//        return jdbcTemplate.query("SELECT * FROM tasks",ROW_MAPPER);
    }

    @Override
    public boolean deleteById(Integer id) {
        return jdbcTemplate.update("DELETE FROM tasks WHERE id =?",id)>0;
    }
}
