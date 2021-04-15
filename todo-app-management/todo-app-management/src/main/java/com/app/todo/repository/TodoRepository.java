package com.app.todo.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.todo.model.Todo;

public interface TodoRepository extends JpaRepository<Todo , Long> {
	
	Optional < Todo > findById(long id);

}
