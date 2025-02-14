package com.carloshsrosa.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carloshsrosa.entities.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@GetMapping
	public ResponseEntity<List<User>> users() {
		User u1 = new User("1", "Dan Purple", "dan@gmail.com");
		User u2 = new User("2", "Marc Brown", "marc@gmail.com");
		List<User> users = Arrays.asList(u1, u2);
		return ResponseEntity.ok().body(users);
	}

}
