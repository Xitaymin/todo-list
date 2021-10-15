package com.xitaymin.todolist.entity;

import javax.validation.constraints.NotBlank;


public class Task {
    private Integer id;
    @NotBlank
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
