package com.in28minutes.rest.webservices.restful_web_services.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.in28minutes.rest.webservices.restful_web_services.jpa.PostRepository;
import com.in28minutes.rest.webservices.restful_web_services.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	// GET /users
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers()
	{
		return userRepository.findAll();
	}
	
	// GET /users/{id}
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveParticularUser(@PathVariable("id") int id)
	{
		Optional<User> user = userRepository.findById(id);
		
		if(user.isEmpty())
			throw new UserNotFoundException("id:"+id);
		
		// Wrapping the domain object, user, with the help of EntityModel class.
		EntityModel<User> entityModel= EntityModel.of(user.get());
		
		// Adding the links to the entityModel object using WebMvcLinkBuilder class.
		WebMvcLinkBuilder link= linkTo(methodOn(this.getClass()).retrieveAllUsers());		// Creating the link
		entityModel.add(link.withRel("all-users"));											// Named the link and attached it to the entityModel object
		
		return entityModel;
	}
	
	// DELETE /users/{id}
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable("id") int id)
	{
		userRepository.deleteById(id);
	}
		
	
	// POST /users
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) 
	{
		User savedUser= userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedUser.getId())
						.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	
	// Retrieving all the posts of a user.
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrievePostsForUser(@PathVariable("id") int id)
	{
		Optional<User> user = userRepository.findById(id);
		
		if(user.isEmpty())
			throw new UserNotFoundException("id:"+id);
		
		return user.get().getPosts();
	}
	
	// Creating the post.
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostsForUser(@PathVariable("id") int id, @Valid @RequestBody Post post )
	{
		Optional<User> user = userRepository.findById(id);
		
		if(user.isEmpty())
			throw new UserNotFoundException("id:"+id);
		
		post.setUser(user.get());
		
		Post savedPost = postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri();

		return ResponseEntity.created(location).build();	
	}
	
	// Fetching the particular post of a user
	@GetMapping("/jpa/users/{id}/posts/{pId}")
	public Optional<Post> retrieveSpecificUserPost(@PathVariable("id") int id,
										 @PathVariable("pId") int pId)
	{
		User user = userRepository.findById(id).get();
		
		List<Post> posts = user.getPosts();
		
		
		return posts.stream().filter(p -> p.getId() == pId)
                .findFirst();
	}
}
