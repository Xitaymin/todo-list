package com.xitaymin.todolist.entity;

import javax.validation.constraints.NotBlank;


public class Task {
    private Long id;
    @NotBlank
    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
