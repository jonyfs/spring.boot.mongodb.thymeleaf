package br.com.jonyfs.spring.boot.mongodb.thymeleaf.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "users")
public class User extends ResourceSupport {

	@Id
	protected String id;

	@Field
	@NotEmpty
	String name;

	@Field
	@Email
	String email;

	@JsonCreator
	public User(@JsonProperty("name") String name, @JsonProperty("email") String email) {
		super();
		this.name = name;
		this.email = email;
	}
	
	@JsonGetter("id")
	public String getUserId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [");
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (email != null) {
			builder.append("email=");
			builder.append(email);
		}
		builder.append("]");
		return builder.toString();
	}
}
