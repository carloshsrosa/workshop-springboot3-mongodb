package com.carloshsrosa.repositories;

import java.time.Instant;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.carloshsrosa.entities.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	public List<Post> findByTitleContainingIgnoreCase(String text);

	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	public List<Post> searchTitle(String text);

	@Query("{ $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSearch(String text, Instant minDate, Instant maxDate);

}
