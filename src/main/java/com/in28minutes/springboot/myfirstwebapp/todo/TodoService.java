package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {
	
	private static List<Todo> todos = new ArrayList<>();
	private static int todosCount = 0;
	
	static {
		todos.add(new Todo(++todosCount, "in28minutes","Learn AWS", 
							LocalDate.now().plusYears(1), "false" ));
		todos.add(new Todo(++todosCount, "in28minutes","Learn DevOps", 
				LocalDate.now().plusYears(2), "false" ));
		todos.add(new Todo(++todosCount, "in28minutes","Learn Full Stack Development", 
				LocalDate.now().plusYears(3), "false" ));
		todos.add(new Todo(++todosCount, "Arjun","Learn springboot", 
				LocalDate.now().plusYears(1), "false" ));
        todos.add(new Todo(++todosCount, "Arjun","Learn git", 
        		LocalDate.now().plusYears(2), "false" ));
        todos.add(new Todo(++todosCount, "Arjun","Learn sql", 
        		LocalDate.now().plusYears(3), "false" ));
	}
	
	public List<Todo> findByUsername(String username){
		
		//it will return todos where the given username by the user matches
		Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
		
		return todos.stream().filter(predicate).toList();
	}
	
	public void addTodo(String username, String description, LocalDate targetDate, String done) {
		Todo todo = new Todo(++todosCount, username, description, targetDate, done);
		todos.add(todo);
	}
	
	public void deleteById(int id) {
		
		//Lambda function is used here
		//"todo" is a paramter so the following function named "predicate" will check if every todo passed in this
		// matches the "id" if yes then it will true or false
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		
		todos.removeIf(predicate);
	}

	public Todo findById(int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		Todo todo = todos.stream().filter(predicate).findFirst().get();
		
		return todo;
	}

	public void updateTodo(@Valid Todo todo) {
		deleteById(todo.getId());
		todos.add(todo);
		
	}
}