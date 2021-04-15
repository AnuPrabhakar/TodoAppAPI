package com.app.todo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "TODO_ITEMS")
@Data
public class Todo {

	public Todo(String itemDescription, String tagName, String reminder, boolean isDone) {
		super();
		this.itemDescription = itemDescription;
		this.tagName = tagName;
		this.reminder = reminder;
		this.isDone = isDone;
	}

	public Todo() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String itemDescription;
	
	private String tagName;
	
	private String reminder;
	
	private boolean isDone;
	
	private String status;
	
	private String modifiedOn;
	
}
