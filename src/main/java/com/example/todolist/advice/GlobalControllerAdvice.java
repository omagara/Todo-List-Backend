package com.example.todolist.advice;

import com.example.todolist.exception.TodoItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class GlobalControllerAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public ErrorResponse todoItemNotFoundExceptionHandling(TodoItemNotFoundException todoItemNotFoundException){
        return new ErrorResponse(todoItemNotFoundException.getMessage());
    }
}
