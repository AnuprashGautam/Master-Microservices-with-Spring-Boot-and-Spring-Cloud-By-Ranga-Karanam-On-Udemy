package com.in28minutes.learn_jpa_and_hibernate.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.in28minutes.learn_jpa_and_hibernate.course.Course;
import com.in28minutes.learn_jpa_and_hibernate.course.jdbc.CourseJdbcRepository;
import com.in28minutes.learn_jpa_and_hibernate.course.jpa.CourseJpaRepository;
import com.in28minutes.learn_jpa_and_hibernate.course.springdatajpa.CourseSpringDataJpaRepository;

import jakarta.transaction.Transactional;

@Component
@Transactional
public class CourseCommandLineRunner implements CommandLineRunner{

//	@Autowired
//	private CourseJdbcRepository repository;

//	@Autowired
//	private CourseJpaRepository repository;
	
	@Autowired
	private CourseSpringDataJpaRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		repository.save(new Course(1, "Learn Azure JPA", "Coding Guru"));
		repository.save(new Course(2, "Learn AWS JPA", "Code with Harry"));
		repository.save(new Course(3, "Learn GCP JPA", "Telusko"));
		repository.save(new Course(4, "Learn k8s JPA", "Telusko"));
		
		
		repository.deleteById(1l);
		
		
		System.out.println(repository.findById(2l));
		System.out.println(repository.findById(3l));
		
		System.out.println(repository.findByAuthor("Telusko"));
		System.out.println(repository.findByName("Learn k8s JPA"));
	}
}
