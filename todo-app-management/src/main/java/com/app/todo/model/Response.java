package com.app.todo.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class Response implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Todo> todoList;
}
