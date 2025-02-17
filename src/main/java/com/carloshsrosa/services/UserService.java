package com.carloshsrosa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carloshsrosa.dto.UserDTO;
import com.carloshsrosa.entities.User;
import com.carloshsrosa.exceptions.ObjectNotFoundException;
import com.carloshsrosa.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(String id) {
		User user = repository.findById(id).orElse(null);
		if (user == null) {
			throw new ObjectNotFoundException("Object not found");
		}
		return user;
	}

	public User insert(User user) {
		return repository.insert(user);
	}

	public User fromDTO(UserDTO userDTO) {
		return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
	}
}
