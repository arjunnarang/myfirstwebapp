package com.in28minutes.springboot.myfirstwebapp.todo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class TodoController {
	
	public TodoController(TodoService todoService) {
		super();
		this.todoService = todoService;
	}

	private TodoService todoService;
		
	
	@RequestMapping("/list-todos")
	public String listAllTodos(ModelMap model) {
		List<Todo> todos = todoService.findByUsername("in28minutes");
		model.addAttribute("todos", todos);
		
		return "listTodos";
	}
	
	// @RequestMapping("/add-todo") is handling all type of requests like GET, POST so when we click on "submit"
	//in the form created in todo.jsp, it will redirect to add-todo page only as POST method is being handled by
	// same function. So if we need the function to handle GET requests only we need to mention this
	// @RequestMapping(value="/add-todo", method=RequestMethod.GET)
	@RequestMapping(value="/add-todo", method=RequestMethod.GET)
	public String showNewTodoPage() {
		
		return "todo";
	}

	//this function will handle POST requests like when we submit form POST request is hit so 
	//those inputs will handled by this function
	@RequestMapping(value="/add-todo", method=RequestMethod.POST)
	public String addNewTodo() {
		
		// this return statement will redirect to emptu listTodos page
		//empty because we havent written that logic of listing todos just like present in "listAllTodos" function
		//so we redirect this function to "list-todos" page
		return "redirect:list-todos";
	}

}
