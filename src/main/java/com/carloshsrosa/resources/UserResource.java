package com.carloshsrosa.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.carloshsrosa.dto.UserDTO;
import com.carloshsrosa.entities.Post;
import com.carloshsrosa.entities.User;
import com.carloshsrosa.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> users = service.findAll();
		List<UserDTO> usersDTO = users.stream().map(u -> new UserDTO(u)).toList();
		return ResponseEntity.ok().body(usersDTO);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User user = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(user));
	}

	@GetMapping(value = "/{id}/posts")
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
		User user = service.findById(id);
		return ResponseEntity.ok().body(user.getPosts());
	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO userDTO) {
		User user = service.fromDTO(userDTO);
		user = service.insert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable String id, @RequestBody UserDTO userDTO) {
		User user = service.fromDTO(userDTO);
		user.setId(id);
		service.update(user);
		return ResponseEntity.ok().body(new UserDTO(user));
	}
}
