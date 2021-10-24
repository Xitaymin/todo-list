package com.xitaymin.todolist.entity;

import javax.validation.constraints.NotBlank;
import java.util.Objects;


public class Task {
    private Integer id;
    @NotBlank
    private String text;

    public Task() {
    }

    public Task(Integer id, @NotBlank String text) {
        this.id = id;
        this.text = text;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return text.equals(task.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }
}
