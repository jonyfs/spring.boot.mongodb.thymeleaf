package br.com.jonyfs.spring.boot.mongodb.thymeleaf.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.jonyfs.spring.boot.mongodb.thymeleaf.domain.User;

public interface UserRepository extends MongoRepository<User, String> {

}
