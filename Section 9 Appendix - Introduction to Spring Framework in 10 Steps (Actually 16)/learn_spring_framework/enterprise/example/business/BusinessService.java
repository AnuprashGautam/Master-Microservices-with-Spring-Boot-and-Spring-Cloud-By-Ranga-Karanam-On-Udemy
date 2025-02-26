package com.in28minutes.spring.learn_spring_framework.enterprise.example.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.in28minutes.spring.learn_spring_framework.enterprise.example.data.DataService;

@Component
public class BusinessService{
	
//	@Autowired
//	public BusinessService(DataService dataService) {
//		super();
//		System.out.println("Constructor based dependency injection.");
//		this.dataService = dataService;
//	}
	

	private DataService dataService;
		
	public DataService getDataService() {
		return dataService;
	}

	@Autowired
	public void setDataService(DataService dataService) {
		this.dataService = dataService;
		System.out.println("Setter based dependency injection.");
	}

	public long calculateSum() {
		List<Integer> data = dataService.getData();
		return data.stream().reduce(Integer::sum).get();
	}
}


