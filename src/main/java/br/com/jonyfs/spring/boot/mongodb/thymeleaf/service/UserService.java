package br.com.jonyfs.spring.boot.mongodb.thymeleaf.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.jonyfs.spring.boot.mongodb.thymeleaf.domain.User;

public interface UserService {

	public User findById(String id);
	
	public User save(User user);

	public Page<User> findAll(Pageable pageable);

}
