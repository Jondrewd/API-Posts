package com.jondrewd.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jondrewd.workshopmongo.domain.Post;
import com.jondrewd.workshopmongo.domain.User;
import com.jondrewd.workshopmongo.dto.AutorDTO;
import com.jondrewd.workshopmongo.dto.CommentDTO;
import com.jondrewd.workshopmongo.repository.PostRepository;
import com.jondrewd.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");
    
        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu", "Partiu.", new AutorDTO(maria));
        Post post2 = new Post(null, sdf.parse("23/03/2018"), "Partiu dnv", "Partiu dnv.", new AutorDTO(alex));

        CommentDTO c1= new CommentDTO("Boa viagem", sdf.parse("21/03/2018"), new AutorDTO(bob));
        CommentDTO c2= new CommentDTO("Boa viagem 2", sdf.parse("22/03/2018"), new AutorDTO(alex));
        CommentDTO c3= new CommentDTO("Boa viagem tambem", sdf.parse("23/03/2018"), new AutorDTO(bob));

        post1.getComments().addAll(Arrays.asList(c1,c2));
        post2.getComments().addAll(Arrays.asList(c3));
        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1));
        alex.getPosts().addAll(Arrays.asList(post2));
        userRepository.saveAll(Arrays.asList(maria, alex));

    }   
}