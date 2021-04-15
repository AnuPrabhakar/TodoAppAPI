package com.app.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Data;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Data
public class ResourceNotFoundException {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message){
        super();
    }
}
