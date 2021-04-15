package com.app.todo.service;

import java.util.Optional;

import com.app.todo.model.Todo;

public interface TodoService {

	Optional < Todo > getTodoById(long id);

	void updateTodoItem(Todo todo);

	void addTodoItem(String desc, String tagName, String reminder, boolean isDone);

	void deleteTodoItem(long id);

	void saveTodoItem(Todo todo);
}
