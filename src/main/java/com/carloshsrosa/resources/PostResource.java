package com.carloshsrosa.resources;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carloshsrosa.entities.Post;
import com.carloshsrosa.resources.util.URL;
import com.carloshsrosa.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	PostService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post post = service.findById(id);
		return ResponseEntity.ok().body(post);
	}

	@GetMapping(value = "/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(defaultValue = "") String text) {
		text = URL.decodeParam(text);
		List<Post> posts = service.findByTitle(text);
		return ResponseEntity.ok().body(posts);
	}

	@GetMapping(value = "/fullsearch")
	public ResponseEntity<List<Post>> fullSearch(@RequestParam(defaultValue = "") String text,
			@RequestParam(defaultValue = "") String minDate, @RequestParam(defaultValue = "") String maxDate) {
		text = URL.decodeParam(text);
		Instant auxiliarMinDate = Instant.now().minus(360, ChronoUnit.DAYS);
		Instant auxiliarMaxDate = Instant.now();
		Instant instantMinDate = null;
		Instant instantMaxDate = null;
		if (!minDate.isBlank()) {
			instantMinDate = URL.convertDate(minDate, auxiliarMinDate);
		} else {
			instantMinDate = auxiliarMinDate;
		}
		if (!maxDate.isBlank()) {
			instantMaxDate = URL.convertDate(maxDate, auxiliarMaxDate);
		} else {
			instantMaxDate = auxiliarMaxDate;
		}
		instantMaxDate = instantMaxDate.plus(24, ChronoUnit.HOURS);
		List<Post> posts = service.fullSearch(text, instantMinDate, instantMaxDate);
		return ResponseEntity.ok().body(posts);
	}
}
