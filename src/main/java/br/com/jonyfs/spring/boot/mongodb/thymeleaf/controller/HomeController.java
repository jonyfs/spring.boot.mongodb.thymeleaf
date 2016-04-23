package br.com.jonyfs.spring.boot.mongodb.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "templates/{page}", method = RequestMethod.GET)
	public String getLayout(@PathVariable("page") String page) {
		return page;
	}
}