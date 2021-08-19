package service;

import entity.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import repository.TodoListRepository;

import java.util.List;

@Service
public class TodoListService {
    @Autowired
    private TodoListRepository todoListRepository;

    public TodoListService(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    public List<TodoItem> getAllTodoItems(){
        return todoListRepository.findAll();
    }
}
