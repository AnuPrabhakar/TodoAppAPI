package com.app.todo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.todo.model.Todo;
import com.app.todo.repository.TodoRepository;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoRepository todoRepository;
	
	@Override
	public Optional<Todo> getTodoById(long id) {
		return todoRepository.findById(id);
	}

	@Override
	public void updateTodoItem(Todo todo) {
		todoRepository.save(todo);
	}

	@Override
	public Todo addTodoItem(Todo todo) {
		return todoRepository.save(new Todo(todo.getItemDescription(),todo.getTagName(),todo.getReminder()));
	}

	@Override
	public void deleteTodoItem(Todo todo) {
			todoRepository.delete(todo);
	}

	@Override
	public void saveTodoItem(Todo todo) {
		todoRepository.save(todo);
	}

	@Override
	public Optional<Todo> findItemByDesc(String itemDesc) {
		return todoRepository.findItemByDesc(itemDesc);

	}


}
