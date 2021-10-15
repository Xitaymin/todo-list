package com.xitaymin.todolist.dao;

import com.xitaymin.todolist.entity.Task;
import com.xitaymin.todolist.exceptions.EntityNotFoundException;
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
    @Override
    public void deleteAll() {
        jdbcTemplate.update("SELECT * FROM tasks");
    }

    private static final RowMapper<Task> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Task.class);
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert taskInsert;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TaskDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.taskInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("tasks")
                .usingGeneratedKeyColumns("id");
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Task upsert(Task task) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(task);
        if (task.getId()==null){
//            BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(task);
            Number newKey = taskInsert.executeAndReturnKey(parameterSource);
            task.setId(newKey.intValue());
            return task;
        }
        else {
            int affected = namedParameterJdbcTemplate.update("UPDATE tasks SET description=:text where id=:id",parameterSource);
            if (affected==0){throw new EntityNotFoundException(String.format(TASK_NOT_FOUND,task.getId()));
            }
        }
        return task;
    }

    @Override
    public Collection<Task> findAll() {
        //todo format case
        return jdbcTemplate.query("SELECT * FROM tasks order by id", (rs, rowNum) -> {
            Task task = new Task();
            task.setId(rs.getInt("id"));
            task.setText(rs.getString("description"));
            return task;
        });

//        return jdbcTemplate.query("SELECT * FROM tasks",ROW_MAPPER);
    }

    @Override
    public boolean deleteById(Integer id) {
        return jdbcTemplate.update("DELETE FROM tasks WHERE id =?",id)>0;
    }
}
