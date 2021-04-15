package com.app.todo.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Request implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4591859491732611682L;
	
	private List<Todo> requests;

}
