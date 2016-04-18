package br.com.jonyfs.spring.boot.mongodb.thymeleaf.service.impl;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.jonyfs.spring.boot.mongodb.thymeleaf.domain.User;
import br.com.jonyfs.spring.boot.mongodb.thymeleaf.repository.UserRepository;
import br.com.jonyfs.spring.boot.mongodb.thymeleaf.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	UserRepository userRepository;

	@Override
	public User findById(String id) {
		return userRepository.findOne(id);
	}

	@Override
	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}
}
