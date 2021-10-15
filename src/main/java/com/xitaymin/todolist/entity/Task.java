package com.xitaymin.todolist.entity;

import javax.validation.constraints.NotBlank;


public class Task {
    private Integer id;
    @NotBlank
    private String text;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
