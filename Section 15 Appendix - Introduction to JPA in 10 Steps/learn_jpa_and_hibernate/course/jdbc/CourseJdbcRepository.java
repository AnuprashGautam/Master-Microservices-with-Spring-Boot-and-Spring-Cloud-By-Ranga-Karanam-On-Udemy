package com.in28minutes.learn_jpa_and_hibernate.course.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.in28minutes.learn_jpa_and_hibernate.course.Course;

@Repository  // When a class communicate with the database.
public class CourseJdbcRepository {
	
	@Autowired
	private JdbcTemplate springJdbcTemplate;
	
	private String 	INSERT_QUERY=
			"""
				insert into course(id,name,author)
			    values (?, ?, ?);
			""";
	
	private String 	DELETE_QUERY=
			"""
				delete from course where id=?;
			""";
	
	private String 	SELECT_QUERY=
			"""
				select * from course where id=?;
			""";
	
	
	public void insert(Course course)
	{
		springJdbcTemplate.update(INSERT_QUERY, course.getId(), course.getName(), course.getAuthor());
	}
	
	public void deleteById(long id)
	{
		springJdbcTemplate.update(DELETE_QUERY, id);
	}
	
	public Course findById(long id)
	{
		// ResultSet => Bean => Row Mapper
		
		return springJdbcTemplate.queryForObject(SELECT_QUERY,
										new BeanPropertyRowMapper<>(Course.class),
										id);
	}
}
