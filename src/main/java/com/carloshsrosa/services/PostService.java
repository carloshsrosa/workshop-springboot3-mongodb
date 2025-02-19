package com.carloshsrosa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carloshsrosa.entities.Post;
import com.carloshsrosa.exceptions.ObjectNotFoundException;
import com.carloshsrosa.repositories.PostRepository;

@Service
public class PostService {

	@Autowired
	PostRepository repository;

	public Post findById(String id) {
		Post post = repository.findById(id).orElse(null);
		if (post == null) {
			throw new ObjectNotFoundException("Object not found");
		}
		return post;
	}

}
