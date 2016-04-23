package br.com.jonyfs.spring.boot.mongodb.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

	@RequestMapping("/users")
	public String index() {
		return "users";
	}	
}