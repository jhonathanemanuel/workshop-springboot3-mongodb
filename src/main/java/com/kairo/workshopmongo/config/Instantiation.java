package com.kairo.workshopmongo.config;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.kairo.workshopmongo.domain.Post;
import com.kairo.workshopmongo.domain.User;
import com.kairo.workshopmongo.dto.AuthorDTO;
import com.kairo.workshopmongo.dto.CommentDTO;
import com.kairo.workshopmongo.repository.PostRepository;
import com.kairo.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		LocalDate d1 = LocalDate.parse("21/03/2018", fmt);
		LocalDate d2 = LocalDate.parse("23/03/2018", fmt);

		Instant inst1 = d1.atStartOfDay(ZoneId.of("GMT")).toInstant();
		Instant inst2 = d2.atStartOfDay(ZoneId.of("GMT")).toInstant();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");	
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, inst1, "Partiu viagem",
		        "Vou viajar para São Paulo, Abraços!", new AuthorDTO(maria));

		Post post2 = new Post(null, inst2, "Bom dia",
		        "Acordei feliz hoje", new AuthorDTO(maria));
		
//		Post post1 = new Post(null, Instant.from(fmt.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo, Abraços!", new AuthorDTO(maria));
//		Post post2 = new Post(null, Instant.from(fmt.parse("21/03/2018"), "Bom dia", "Acordei feliz hoje", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Boa viagem mano!", LocalDate.parse("21/03/2018", fmt), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite", LocalDate.parse("22/03/2018", fmt), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia", LocalDate.parse("23/03/2018", fmt), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post1.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}
}
