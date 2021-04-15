package com.app.todo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.todo.model.Todo;
import com.app.todo.repository.TodoRepository;

public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoRepository todoRepository;

	@Override
	public Optional < Todo > getTodoById(long id) {
		return todoRepository.findById(id);
	}

	@Override
	public void updateTodoItem(Todo todo) {
		todoRepository.save(todo);
	}

	@Override
	public void addTodoItem(String desc, String tagName, String reminder, boolean isDone) {
		todoRepository.save(new Todo(desc, tagName , reminder , isDone));
	}

	@Override
	public void deleteTodoItem(long id) {
		Optional < Todo > todo = todoRepository.findById(id);
		if (todo.isPresent()) {
			todoRepository.delete(todo.get());
		}
	}

	@Override
	public void saveTodoItem(Todo todo) {
		todoRepository.save(todo);
	}



}
