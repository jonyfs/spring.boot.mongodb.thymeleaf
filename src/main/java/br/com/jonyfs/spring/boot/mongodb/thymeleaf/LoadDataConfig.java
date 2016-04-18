package br.com.jonyfs.spring.boot.mongodb.thymeleaf;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.context.annotation.Configuration;

import br.com.jonyfs.spring.boot.mongodb.thymeleaf.domain.User;
import br.com.jonyfs.spring.boot.mongodb.thymeleaf.service.UserService;

@Configuration
public class LoadDataConfig {

	@Resource
	UserService userService;

	@PostConstruct
	public void init() {
		String[] firstNames = { "John", "Mary", "Joseph", "Paul", "Tim", "Steve", "Marcy","Lucy" };
		String[] lastNames = { "Jobs", "Santos", "Almeida", "Chung", "Cook", "Anderson", "Perez" };

		int i = 0;
		while (i < 50) {
			String firstName = firstNames[RandomUtils.nextInt(firstNames.length - 1)];
			String lastName = lastNames[RandomUtils.nextInt(lastNames.length - 1)];
			String completeName = firstName + " " + lastName;
			String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@test.com";
			userService.save(new User(completeName, email));
			i++;
		}
	}
}
