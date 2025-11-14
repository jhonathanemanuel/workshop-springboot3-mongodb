package com.kairo.workshopmongo.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kairo.workshopmongo.domain.Post;
import com.kairo.workshopmongo.repository.PostRepository;
import com.kairo.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;

	public Post findById(String id) {
	    Optional<Post> user = repo.findById(id);
	    return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Post> findByTitle(String text) {
		return repo.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Instant minDate, Instant maxDate) {
	    // Ajusta maxDate
	    Instant max = maxDate.plusSeconds(86400);
	    return repo.fullSearch(text, minDate, max);
	}



}
