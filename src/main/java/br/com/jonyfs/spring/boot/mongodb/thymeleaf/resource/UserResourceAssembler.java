package br.com.jonyfs.spring.boot.mongodb.thymeleaf.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.jonyfs.spring.boot.mongodb.thymeleaf.controller.UserController;
import br.com.jonyfs.spring.boot.mongodb.thymeleaf.domain.User;

@Component
public class UserResourceAssembler extends ResourceAssemblerSupport<User, Resource> {

	public UserResourceAssembler() {
		super(UserController.class, Resource.class);
	}

	@Override
	public List<Resource> toResources(Iterable<? extends User> users) {
		List<Resource> resources = new ArrayList<Resource>();
		for (User user : users) {
			resources.add(new Resource<User>(user, linkTo(UserController.class).slash(user.getId()).withSelfRel()));
		}
		return resources;
	}

	@Override
	public Resource toResource(User widget) {
		return new Resource<User>(widget, linkTo(UserController.class).slash(widget.getUserId()).withSelfRel());
	}
}
