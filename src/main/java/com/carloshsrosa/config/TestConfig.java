package com.carloshsrosa.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.carloshsrosa.dto.AuthorDTO;
import com.carloshsrosa.dto.CommentDTO;
import com.carloshsrosa.entities.Post;
import com.carloshsrosa.entities.User;
import com.carloshsrosa.repositories.PostRepository;
import com.carloshsrosa.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		postRepository.deleteAll();

		User u1 = new User(null, "Maria Brown", "maria@gmail.com");
		User u2 = new User(null, "Alex Green", "alex@gmail.com");
		User u3 = new User(null, "Bob Grey", "bob@gmail.com");

		userRepository.saveAll(Arrays.asList(u1, u2, u3));

		Post p1 = new Post(null, Instant.parse("2018-03-21T14:29:53.123498Z"), "Partiu viagem",
				"Vou viajar para São Paulo. Abraços!", new AuthorDTO(u3));
		Post p2 = new Post(null, Instant.parse("2018-03-23T04:57:13.561576Z"), "Bom dia", "Acordei feliz hoje!",
				new AuthorDTO(u3));

		CommentDTO c1 = new CommentDTO("Boa viagem mano!", Instant.parse("2018-03-21T08:12:47.874573Z"),
				new AuthorDTO(u2));

		CommentDTO c2 = new CommentDTO("Aproveite!", Instant.parse("2018-03-22T01:41:25.124589Z"),
				new AuthorDTO(u2));
		
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", Instant.parse("2018-03-23T22:14:58.587541Z"),
				new AuthorDTO(u2));
		
		postRepository.saveAll(Arrays.asList(p1, p2));

		u3.getPosts().addAll(Arrays.asList(p1, p2));
		userRepository.save(u3);
		
		p1.getComments().addAll(Arrays.asList(c1,c2));
		p2.getComments().add(c3);
		postRepository.saveAll(Arrays.asList(p1, p2));


	}

}
