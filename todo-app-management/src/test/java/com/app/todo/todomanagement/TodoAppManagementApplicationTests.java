package com.app.todo.todomanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.app.todo.TodoAppManagementApplication;
import com.app.todo.model.Request;
import com.app.todo.model.Response;
import com.app.todo.model.Todo;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TodoAppManagementApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoAppManagementApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	Timestamp timestamp = new Timestamp(System.currentTimeMillis() + (1000*60*60*24) );
	
	@Test
	public void testAddTodoItem_invalidDesc() throws Exception {
		final String baseUrl = "http://localhost:" + port + "/todo/item";
		HttpHeaders headers = new HttpHeaders();
	
		Request request = new Request();
		List<Todo> todoList = new ArrayList<>();
		todoList.add(new Todo("","Work",null));

		request.setRequests(todoList);

		HttpEntity<Request> entity = new HttpEntity<>(request, headers);
		
		ResponseEntity<String> postResponse = restTemplate.exchange(baseUrl, HttpMethod.POST, entity, String.class);
        assertEquals(500, postResponse.getStatusCodeValue());

	}
	
	@Test
	public void testAddTodoItem_successcase() throws Exception {
		final String baseUrl = "http://localhost:" + port + "/todo/item";
		HttpHeaders headers = new HttpHeaders();
		Request request = new Request();
		List<Todo> todoList = new ArrayList<>();
		todoList.add(new Todo("Meeting","Work",timestamp));
		todoList.add(new Todo("App Closure","Work",null));
		todoList.add(new Todo("Learn Dev","Study",null));

		request.setRequests(todoList);

		HttpEntity<Request> entity = new HttpEntity<>(request, headers);
		ResponseEntity<Response> postResponse = restTemplate.exchange(baseUrl, HttpMethod.POST, entity, Response.class);
        assertNotNull(postResponse.getBody());
        assertEquals(201, postResponse.getStatusCodeValue());

	}
	@Test
	public void testUpdateTodoItem_successCase() throws Exception {
		final String baseUrl = "http://localhost:" + port + "/todo/item";
		HttpHeaders headers = new HttpHeaders();
	
		Request request = new Request();
		List<Todo> todoList = new ArrayList<>();
		//updating Reminder, tags , done status
		todoList.add(new Todo(1,"Meeting","WorkNew",timestamp,Boolean.FALSE));
		todoList.add(new Todo(2,"App Dev","WorkNew",timestamp,Boolean.FALSE));
		todoList.add(new Todo(3,"Learn Dev","Study",null,Boolean.TRUE));

		request.setRequests(todoList);

		HttpEntity<Request> entity = new HttpEntity<>(request, headers);
		
		ResponseEntity<String> postResponse = restTemplate.exchange(baseUrl, HttpMethod.PUT, entity, String.class);
        assertEquals(200, postResponse.getStatusCodeValue());

	}
	@Test
	public void testUpdateTodoItem_invalidId() throws Exception {
		final String baseUrl = "http://localhost:" + port + "/todo/item";
		HttpHeaders headers = new HttpHeaders();
	
		Request request = new Request();
		List<Todo> todoList = new ArrayList<>();
		todoList.add(new Todo(1121212,"Meeting","WorkNew",timestamp,Boolean.FALSE));

		request.setRequests(todoList);

		HttpEntity<Request> entity = new HttpEntity<>(request, headers);
		
		ResponseEntity<String> postResponse = restTemplate.exchange(baseUrl, HttpMethod.PUT, entity, String.class);
        assertEquals(404, postResponse.getStatusCodeValue());

	}
	
	@Test
	public void testDeleteTodoItem_successCase() throws Exception {
		final String baseUrl = "http://localhost:" + port + "/todo/item";
		HttpHeaders headers = new HttpHeaders();
	
		Request request = new Request();
		List<Todo> todoList = new ArrayList<>();
		todoList.add(new Todo(2,"App Dev","WorkNew",timestamp,Boolean.FALSE));
		request.setRequests(todoList);

		HttpEntity<Request> entity = new HttpEntity<>(request, headers);
		
		ResponseEntity<String> postResponse = restTemplate.exchange(baseUrl, HttpMethod.DELETE, entity, String.class);
        assertEquals(200, postResponse.getStatusCodeValue());

	}

	@Test
	public void testDeleteTodoItem_invalidId() throws Exception {
		final String baseUrl = "http://localhost:" + port + "/todo/item";
		HttpHeaders headers = new HttpHeaders();
	
		Request request = new Request();
		List<Todo> todoList = new ArrayList<>();
		todoList.add(new Todo(1121212,"Meeting","WorkNew",timestamp,Boolean.FALSE));

		request.setRequests(todoList);

		HttpEntity<Request> entity = new HttpEntity<>(request, headers);
		
		ResponseEntity<String> postResponse = restTemplate.exchange(baseUrl, HttpMethod.DELETE, entity, String.class);
        assertEquals(404, postResponse.getStatusCodeValue());

	}
	
	
	

}
