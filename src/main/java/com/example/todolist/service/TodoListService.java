package com.example.todolist.service;

import com.example.todolist.entity.Todo;
import com.example.todolist.exception.TodoItemNotFoundException;
import com.example.todolist.repository.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoListService {
    @Autowired
    private final TodoListRepository todoListRepository;
    private final String TODO_MESSAGE = "Todo Item not found.";
    public TodoListService(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    public List<Todo> getAllTodoItems(){
        return todoListRepository.findAll();
    }

    public Todo addTodoItem(Todo todo){
        return todoListRepository.save(todo);
    }

    public void deleteTodoItem(Integer id){
        todoListRepository.deleteById(id);
    }

    public Todo updateTodoItem(Integer id, Todo todo){
        Todo retrievedTodoItem = todoListRepository.findById(id)
                .orElseThrow( () -> new TodoItemNotFoundException(TODO_MESSAGE));
        if (retrievedTodoItem != null) {
            retrievedTodoItem.setDone(todo.isDone());
        }
        return todoListRepository.save(retrievedTodoItem);
    }

}
