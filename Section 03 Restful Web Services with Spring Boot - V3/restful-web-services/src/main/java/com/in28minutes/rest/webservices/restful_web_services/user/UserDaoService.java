package com.in28minutes.rest.webservices.restful_web_services.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	// Dao class is used to communicate with database using JPA/Hibernate. But, for the simplicity we are currently usig a static List<User>.
	
	private static List<User> users= new ArrayList<>();
	
	private static int userCount = 0;
	
	static {
		users.add(new User(++userCount,"Adam",LocalDate.now().minusYears(30)));
		users.add(new User(++userCount,"Radha",LocalDate.now().minusYears(13)));
		users.add(new User(++userCount,"Shyam",LocalDate.now().minusYears(20)));
		users.add(new User(++userCount,"Rina",LocalDate.now().minusYears(50)));
		users.add(new User(++userCount,"Riyan",LocalDate.now().minusYears(33)));
	}
	
	public List<User> findAll()
	{
		return users;
	}
	
	public User save(User user)
	{
		user.setId(++userCount);
		users.add(user);
		return user;
	}
	
	public User findOne(int id)
	{
//		User user = null;
//		for(User u: users)
//		{
//			if(u.getId()==id)
//				user=u;
//		}
//		return user;
		
		// Using functional programming.
		Predicate<? super User> predicate = user -> user.getId()==id; 
		return users.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void deleteById(int id)
	{
		Predicate<? super User> predicate = user -> user.getId()==id;
		users.removeIf(predicate);
	}
}
