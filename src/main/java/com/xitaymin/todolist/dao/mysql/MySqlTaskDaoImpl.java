package com.xitaymin.todolist.dao.mysql;

import com.xitaymin.todolist.dao.GenericTaskDaoImpl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MySqlTaskDaoImpl extends GenericTaskDaoImpl {
    public MySqlTaskDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate template) {
        super(jdbcTemplate, template);
    }
}

