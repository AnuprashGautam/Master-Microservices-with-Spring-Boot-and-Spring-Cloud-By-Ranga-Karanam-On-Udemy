package com.in28minutes.rest.webservices.restful_web_services.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
	
	// 1. URI Versioning- Twitter
	
	@GetMapping("/v1/person")
	public PersonV1 getFirstVersionOfPerson()
	{
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getSecondVersionOfPerson()
	{
		return new PersonV2(new Name("Bob","Charlie"));
	}
	
	
	// 2. Request Parameter Versioning- Amazon
	
	@GetMapping(path="/person", params="version=1")
	public PersonV1 getFirstVersionOfPersonRequestParameter()
	{
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(path="/person", params="version=2")
	public PersonV2 getSecondVersionOfPersonRequestParameter()
	{
		return new PersonV2(new Name("Bob","Charlie"));
	}
	
	
	// 3. Header Versioning- Microsoft
	
	@GetMapping(path="/person/header", headers="X-API-VERSION=1")
	public PersonV1 getFirstVersionOfPersonHeader()
	{
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(path="/person/header", headers="X-API-VERSION=2")
	public PersonV2 getSecondVersionOfPersonHeader()
	{
		return new PersonV2(new Name("Bob","Charlie"));
	}
	
	
	// 4. Media Type Versioning- GitHub
	
	@GetMapping(path="/person/accept", produces="application/vnd.company.app-v1+json")
	public PersonV1 getFirstVersionOfPersonAccept()
	{
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(path="/person/header", produces="application/vnd.company.app-v2+json")
	public PersonV2 getSecondVersionOfPersonAccept()
	{
		return new PersonV2(new Name("Bob","Charlie"));
	}
}

