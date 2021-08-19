package com.example.todolist.service;

import com.example.todolist.entity.Todo;
import com.example.todolist.repository.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoListService {
    @Autowired
    private TodoListRepository todoListRepository;

    public TodoListService(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    public List<Todo> getAllTodoItems(){
        return todoListRepository.findAll();
    }

    public Todo addTodoItem(Todo todo){
        return todoListRepository.save(todo);
    }

    public Todo updateTodoItem(Integer id, Todo todo){
        Todo retrievedTodoItem = retrieve(id);
        retrievedTodoItem.setDone(todo.isDone());
        return todoListRepository.save(retrievedTodoItem);
    }

    public void deleteTodoItem(Integer id){
        todoListRepository.deleteById(id);
    }

    public Todo retrieve(Integer id) {
        return todoListRepository.findById(id)
                .orElse(null);
    }
}
