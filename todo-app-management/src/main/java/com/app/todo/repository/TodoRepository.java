package com.app.todo.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.todo.model.Todo;

public interface TodoRepository extends JpaRepository<Todo , Long> {
	
	@Query("select ti from Todo ti where LOWER(ti.itemDescription) = LOWER(:itemDesc)")
	Optional<Todo> findItemByDesc(String itemDesc);
	
}
