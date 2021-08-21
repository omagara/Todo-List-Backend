package com.example.todolist.dto;

public class TodoResponse {

    private int id;
    private String text;
    private boolean done;

    public TodoResponse(String text, boolean done) {
        this.text = text;
        this.done = done;
    }

    public TodoResponse() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
