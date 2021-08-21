package com.example.todolist.controller;

import com.example.todolist.dto.TodoRequest;
import com.example.todolist.dto.TodoResponse;
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
    public List<TodoResponse> getAllTodoItems(){
        return todoMapper.toResponse(todoListService.getAllTodoItems());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse addTodoItem(@RequestBody TodoRequest todoRequest){
        Todo todo = todoListService.addTodoItem(todoMapper.toEntity(todoRequest));
        return todoMapper.toResponse(todo);
    }

    @PutMapping("/{todoItemId}")
    public TodoResponse updateTodoItem (@PathVariable Integer todoItemId, @RequestBody TodoRequest todoRequest){
        Todo todo = todoListService.updateTodoItem(todoItemId, todoMapper.toEntity(todoRequest));
        return todoMapper.toResponse(todo);
    }

    @DeleteMapping("/{todoItemId}")
    public void deleteTodoItem (@PathVariable Integer todoItemId){
        todoListService.deleteTodoItem(todoItemId);
    }
}
