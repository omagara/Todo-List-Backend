package com.example.todolist.controller;

import com.example.todolist.dto.TodoRequest;
import com.example.todolist.entity.Todo;
import com.example.todolist.mapper.TodoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.todolist.service.TodoListService;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoListController {
    @Autowired
    private final TodoListService todoListService;
    @Autowired
    private TodoMapper todoMapper;

    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @GetMapping
    public List<Todo> getAllTodoItems(){
        return todoListService.getAllTodoItems();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo addTodoItem(@RequestBody TodoRequest todoRequest){
        return todoListService.addTodoItem(todoMapper.toEntity(todoRequest));
    }

    @PutMapping("/{todoItemid}")
    public Todo updateTodoItem (@PathVariable Integer todoItemid, @RequestBody TodoRequest todoRequest){
        return todoListService.updateTodoItem(todoItemid, todoMapper.toEntity(todoRequest));
    }

    @DeleteMapping("/{todoItemid}")
    public void deleteTodoItem (@PathVariable Integer todoItemid){
        todoListService.deleteTodoItem(todoItemid);
    }
}
