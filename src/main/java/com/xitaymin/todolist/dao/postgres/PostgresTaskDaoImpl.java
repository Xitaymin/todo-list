package com.xitaymin.todolist.dao.postgres;

import com.xitaymin.todolist.dao.GenericTaskDaoImpl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PostgresTaskDaoImpl extends GenericTaskDaoImpl {
    public PostgresTaskDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate template) {
        super(jdbcTemplate, template);
    }
}
