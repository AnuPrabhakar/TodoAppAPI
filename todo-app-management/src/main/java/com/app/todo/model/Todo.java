package com.app.todo.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name = "TODO_ITEMS")
@Data
public class Todo {

	public Todo(@Valid @NotNull @NotEmpty String itemDescription, String tagName, Timestamp reminder, boolean isDone) {
		super();
		this.itemDescription = itemDescription;
		this.tagName = tagName;
		this.reminder = reminder;
		this.isDone = isDone;
	}
	
	public Todo(long id,String itemDescription, String tagName, Timestamp reminder, boolean isDone) {
		super();
		this.id = id;
		this.itemDescription = itemDescription;
		this.tagName = tagName;
		this.reminder = reminder;
		this.isDone = isDone;
	}
	
	public Todo(@Valid @NotNull @NotEmpty String itemDescription, String tagName, Timestamp reminder) {
		super();
		this.itemDescription = itemDescription;
		this.tagName = tagName;
		this.reminder = reminder;
	}

	public Todo() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "item_desc")
	private String itemDescription;
	
	@Column(name = "tag_name")
	private String tagName;
	
	@Column(name = "reminder" ,nullable=true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Timestamp reminder;
	
	@Column(name = "is_done") // If true, then status is complete.Item Can be restored by setting this flag to false
	private boolean isDone;
	
	@Transient
	private String message;
	
}
