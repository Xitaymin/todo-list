package com.xitaymin.todolist.dao;

import com.xitaymin.todolist.entity.Task;
import com.xitaymin.todolist.service.exceptions.NotFoundResourceException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Collection;

import static com.xitaymin.todolist.service.TaskServiceImpl.TASK_NOT_FOUND;

@Repository
public class TaskDaoImpl implements TaskDao {
    private static final RowMapper<Task> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Task.class);
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert taskInsert;
    private final NamedParameterJdbcTemplate template;

    public TaskDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate template) {
        this.jdbcTemplate = jdbcTemplate;
        this.taskInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("tasks").usingGeneratedKeyColumns("id");
        this.template = template;
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM tasks");
    }

    @Override
    public Task upsert(Task task) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(task);
        if (task.getId() == null) {
            Number newKey = taskInsert.executeAndReturnKey(parameterSource);
            task.setId(newKey.intValue());
            return task;
        } else {
            int affected = template.update("UPDATE tasks SET text=:text WHERE id=:id", parameterSource);
            if (affected == 0) {
                throw new NotFoundResourceException(String.format(TASK_NOT_FOUND, task.getId()));
            }
        }
        return task;
    }

    @Override
    public Collection<Task> findAll() {
        return jdbcTemplate.query("SELECT * FROM tasks", ROW_MAPPER);
    }

    @Override
    public boolean deleteById(Integer id) {
        return jdbcTemplate.update("DELETE FROM tasks WHERE id =?", id) > 0;
    }
}
