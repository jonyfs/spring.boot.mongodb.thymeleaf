package br.com.jonyfs.spring.boot.mongodb.thymeleaf.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.jonyfs.spring.boot.mongodb.thymeleaf.domain.User;
import br.com.jonyfs.spring.boot.mongodb.thymeleaf.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Resource
	UserService userService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public HttpEntity<User> findById(@PathVariable String id) {
		User user = userService.findById(id);
		user.add(linkTo(methodOn(UserController.class).findById(id)).withSelfRel());
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public HttpEntity<Page<User>> findAll(Pageable pageable) {
		Page<User> page = userService.findAll(pageable);
		return new ResponseEntity<Page<User>>(page, HttpStatus.OK);
	}
}
