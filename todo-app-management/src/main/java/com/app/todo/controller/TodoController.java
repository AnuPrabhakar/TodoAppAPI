package com.app.todo.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.todo.exception.ResourceNotFoundException;
import com.app.todo.model.Request;
import com.app.todo.model.Response;
import com.app.todo.model.Todo;
import com.app.todo.repository.TodoRepository;
import com.app.todo.service.TodoService;

@RestController
@RequestMapping("/")
public class TodoController {

	@Autowired
	TodoService todoService;

	@Autowired
	private TodoRepository todoRepository;

	@GetMapping("/todo/item")
	public  List < Todo >  getAllTodoItems(){
		return todoRepository.findAll();
	}

	@PostMapping("/todo/item")
	public ResponseEntity<Response> addTodoList(@Valid @RequestBody Request request) throws Exception{
		List<Todo> todoList = new ArrayList<>();
		Response response= new Response();
		for (Todo item : request.getRequests()) 
		{
			if(Strings.isBlank(item.getItemDescription())) {
				throw new ValidationException(" Item Description cannot be empty");
			}
			
			if(item.getReminder()!=null) {
				 try {
					 	SimpleDateFormat srcFormatter=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					    item.setReminder(new Timestamp(srcFormatter.parse(item.getReminder().toString()).getTime()));
					  }
					 catch (  ParseException e) {
							throw new ValidationException("Reminder Date/time should be in dd/MM/yyyy HH:mm:ss format");
					 }
			}

			Optional<Todo> obj = todoService.findItemByDesc(item.getItemDescription()) ;

			if(obj.isPresent()) {
				throw new ValidationException("TODO Item Description already exists :: " + item.getItemDescription());
			}else {
				Todo newItem = todoRepository.save(new Todo(item.getItemDescription(),item.getTagName(),item.getReminder(),item.isDone()));
				newItem.setMessage("Item added to TODO List Successfully");
				todoList.add(newItem);
			}
		}
		response.setTodoList(todoList);
		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}

	@PutMapping("/todo/item")
	public ResponseEntity<Response> updateTodoList(@Valid @RequestBody Request request) throws ResourceNotFoundException{
		List<Todo> todoList = new ArrayList<>();
		for (Todo item : request.getRequests()) 
		{	
			Optional <Todo> obj = todoService.getTodoById(item.getId());

			if(!obj.isPresent()) {
				throw new ResourceNotFoundException("TODO Item doesn't exist for the given ID :: " + item.getId());
			}

			obj.get().setDone(item.isDone());
			obj.get().setItemDescription(item.getItemDescription());
			obj.get().setReminder(item.getReminder());
			obj.get().setTagName(item.getTagName());

			todoService.updateTodoItem(obj.get());
			item.setMessage("Item Details updated Successfully");
			todoList.add(item);

		}
		Response response= new Response();
		response.setTodoList(todoList);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@DeleteMapping("/todo/item")
	public ResponseEntity<Response> deleteTodoItem(@Valid @RequestBody Request request) throws ResourceNotFoundException{
		List<Todo> todoList = new ArrayList<>();
		for (Todo item : request.getRequests()) 
		{
			Optional <Todo> obj = todoService.getTodoById(item.getId());
			if(!obj.isPresent()) {
				throw new ResourceNotFoundException("TODO Item doesn't exist for the given ID :: " + item.getId());
			}else {
				todoService.deleteTodoItem(obj.get());
				item.setMessage("Item deleted successfully from TODO List");
				todoList.add(item);
			}

		}
		Response response= new Response();
		response.setTodoList(todoList);		
		return new ResponseEntity<>(response, HttpStatus.OK);

	}


}
