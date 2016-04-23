package br.com.jonyfs.spring.boot.mongodb.thymeleaf.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.jonyfs.spring.boot.mongodb.thymeleaf.domain.User;
import br.com.jonyfs.spring.boot.mongodb.thymeleaf.repository.UserRepository;
import br.com.jonyfs.spring.boot.mongodb.thymeleaf.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);


	@Resource
	UserRepository userRepository;

	@Override
	public User findById(String id) {
		LOGGER.debug("Finding {}...",id);
		return userRepository.findOne(id);
	}

	@Override
	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public User save(User user) {
		LOGGER.debug("Saving {}...",user);
		return userRepository.save(user);
	}
}
