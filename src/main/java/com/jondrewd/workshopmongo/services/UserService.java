package com.jondrewd.workshopmongo.services;
import com.jondrewd.workshopmongo.domain.User;
import com.jondrewd.workshopmongo.repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();    
    }
}
