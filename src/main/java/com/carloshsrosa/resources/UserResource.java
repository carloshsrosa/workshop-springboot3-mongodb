package com.carloshsrosa.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carloshsrosa.dto.UserDTO;
import com.carloshsrosa.entities.User;
import com.carloshsrosa.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<List<UserDTO>> users() {
		List<User> users = service.findAll();
		List<UserDTO> usersDTO = users.stream().map(u -> new UserDTO(u)).toList();
		return ResponseEntity.ok().body(usersDTO);
	}

}
