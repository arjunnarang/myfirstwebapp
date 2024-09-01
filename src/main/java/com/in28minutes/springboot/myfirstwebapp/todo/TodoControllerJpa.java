package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {
	
	public TodoControllerJpa(TodoRepository todoRepository) {
		super();
		//this.todoService = todoService;
		this.todoRepository = todoRepository;
	}

	//private TodoService todoService;
	private TodoRepository todoRepository;
	
	@RequestMapping("/list-todos")
	public String listAllTodos(ModelMap model) {
		String username = getLoggedinUsername(model);
		//List<Todo> todos = todoService.findByUsername(username);
		List<Todo> todos = todoRepository.findByUsername(username);
		model.addAttribute("todos", todos);
		
		return "listTodos";
	}
	
	// @RequestMapping("/add-todo") is handling all type of requests like GET, POST so when we click on "submit"
	//in the form created in todo.jsp, it will redirect to add-todo page only as POST method is being handled by
	// same function. So if we need the function to handle GET requests only we need to mention this
	// @RequestMapping(value="/add-todo", method=RequestMethod.GET)
	@RequestMapping(value="/add-todo", method=RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
		
		//this hardcoded new todo object needs to be added so as to support "form backing" implemented in todo.jsp
		String username = getLoggedinUsername(model);
		Todo todo = new Todo(0,username, "", LocalDate.now().plusYears(1), false);
		
		// modelAttribute added in todo.jsp can be find attribute named "todo" in model 
		model.put("todo", todo);
		return "todo";
	}


	//this function will handle POST requests like when we submit form POST request is hit so 
	//those inputs will handled by this function
	@RequestMapping(value="/add-todo", method=RequestMethod.POST)
//	public String addNewTodo(@RequestParam String description, ModelMap model) {
	// @Valid is for validation
    public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		
		//BindingResult helps in handling Size errors
		if(result.hasErrors()) {
			return "todo";
		}
		//Getting username from model
		String username = getLoggedinUsername(model);
		todo.setUsername(username);
		
		todoRepository.save(todo);
		
		
		//todoService.addTodo(username, todo.getDescription(),todo.getTargetDate(), todo.isDone());

		return "redirect:list-todos";
	}
	
	@RequestMapping("/delete-todo")
	public String deleteTodo(@RequestParam int id) {
		
		todoRepository.deleteById(id);
		
		//todoService.deleteById(id);
		return "redirect:list-todos";
	}
	
	//ModelMap is used whenever we need to add data to .jsp pages or retrieve already added data from jsp pages
	//Here we took "id" as a param and then send it to todoService to find us the todo with that id
	//then we added that todo to model and the paramter "todo" should be matching modelAttribute="todo" in todo.jsp
	//nwow this model will populate the todo.jsp accordingly
	
	@RequestMapping(value="/update-todo", method=RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
//		Todo todo = todoService.findById(id);
		
		Todo todo = todoRepository.findById(id).get();
		model.addAttribute(todo);
		return "todo";
	}
	
	@RequestMapping(value="/update-todo", method=RequestMethod.POST)
	//When submit button is hit, this post method is hit and the input values are returned in "todo" object in following function through form backing
	//(direct binding of form and java bean
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		
		//BindingResult helps in handling Size errors
		if(result.hasErrors()) {
			return "todo";
		}
		//Getting username from model
		String username = getLoggedinUsername(model);
		todo.setUsername(username);
		//calling addTodo function
		//todoService.updateTodo(todo);
		todoRepository.save(todo);

		return "redirect:list-todos";
	}
	
	//We can get username through model but only when we go to login page and put our credentials.
	//If a user directly go to list todos page, the session wont have the username stored
	//So we use spring security to get the username everywhere
	private String getLoggedinUsername(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();;
		return authentication.getName();
	}

}
