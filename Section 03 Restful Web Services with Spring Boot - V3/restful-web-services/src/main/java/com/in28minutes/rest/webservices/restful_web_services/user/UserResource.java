package com.in28minutes.rest.webservices.restful_web_services.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService userDaoService;
	
	// GET /users
	@GetMapping("/users")
	public List<User> retrieveAllUsers()
	{
		return userDaoService.findAll();
	}
	
	// GET /users/{id}
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveParticularUser(@PathVariable("id") int id)
	{
		User user = userDaoService.findOne(id);
		
		if(user==null)
			throw new UserNotFoundException("id:"+id);
		
		// Wrapping the domain object, user, with the help of EntityModel class.
		EntityModel<User> entityModel= EntityModel.of(user);
		
		// Adding the links to the entityModel object using WebMvcLinkBuilder class.
		WebMvcLinkBuilder link= linkTo(methodOn(this.getClass()).retrieveAllUsers());		// Creating the link
		entityModel.add(link.withRel("all-users"));											// Named the link and attached it to the entityModel object
		
		return entityModel;
	}
	
	// DELETE /users/{id}
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable("id") int id)
	{
		userDaoService.deleteById(id);
	}
		
	
	// POST /users
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) 
	{
		User savedUser= userDaoService.save(user);
		
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedUser.getId())
						.toUri();
		
		return ResponseEntity.created(location).build();
	}
}
