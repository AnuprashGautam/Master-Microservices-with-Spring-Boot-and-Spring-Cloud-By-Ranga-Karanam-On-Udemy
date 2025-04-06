package com.in28minutes.rest.webservices.restful_web_services.helloworld;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController							// Exposing REST api
public class HelloWorldController {
	
	private MessageSource messageSource;
	
	public HelloWorldController(MessageSource messageSource)
	{
		this.messageSource=messageSource;
	}

	@RequestMapping(method = RequestMethod.GET, value="/hello-world")
	public String helloWorld()
	{
		return "Hello World";
	}
	
	// This api is returning a bean to the frontend.
	@RequestMapping(method = RequestMethod.GET, value="/hello-world-bean")
	public HelloWorldBean helloWorldBean()
	{
		return new HelloWorldBean("Hello World Bean");
	}
	
	// Request to demonstrate the use of path variable.
	@GetMapping("/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable("name") String name)
	{
		return new HelloWorldBean(String.format("Hello world, %s", name));
	}
	@RequestMapping(method = RequestMethod.GET, value="/hello-world-internationalized")
	public String helloWorldInternationalized()
	{
		Locale locale=LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
		// return "Hello World V2";
	}
}
