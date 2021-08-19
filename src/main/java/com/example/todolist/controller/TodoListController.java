package com.example.todolist.controller;

import com.example.todolist.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.todolist.service.TodoListService;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoListController {
    @Autowired
    private TodoListService todoListService;

    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @GetMapping
    public List<Todo> getAllTodoItems(){
        return todoListService.getAllTodoItems();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo addTodoItem(@RequestBody Todo todo){
        return todoListService.addTodoItem(todo);
    }

    @PutMapping("/{todoItemid}")
    public Todo updateTodoItem (@PathVariable Integer todoItemid, @RequestBody Todo todo){
        return todoListService.updateTodoItem(todoItemid, todo);
    }

    @DeleteMapping("/{todoItemid}")
    public void deleteTodoItem (@PathVariable Integer todoItemid){
        todoListService.deleteTodoItem(todoItemid);
    }

}