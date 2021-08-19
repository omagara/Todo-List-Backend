package service;

import entity.TodoItem;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import repository.TodoListRepository;

import java.util.List;

@Service
public class TodoListService {

    private TodoListRepository todoListRepository;

    public TodoListService(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    public List<TodoItem> getAllTodoItems(){
        return todoListRepository.findAll();
    }
}
