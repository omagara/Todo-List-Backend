package controller;

import entity.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.TodoListService;

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
    public List<TodoItem> getAllTodoItems(){
        return todoListService.getAllTodoItems();
    }
}
