package com.app.todo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.todo.model.Todo;
@Service
public interface TodoService {
	public Optional < Todo > getTodoById(long id);
	
	public Optional < Todo > findItemByDesc(String itemDesc);

	public void updateTodoItem(Todo todo);

	public Todo addTodoItem(Todo todo);

	public void deleteTodoItem(Todo todo);

	public void saveTodoItem(Todo todo);

}
