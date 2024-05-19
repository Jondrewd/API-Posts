package com.jondrewd.workshopmongo.services;
import com.jondrewd.workshopmongo.domain.User;
import com.jondrewd.workshopmongo.dto.UserDTO;
import com.jondrewd.workshopmongo.repository.UserRepository;
import com.jondrewd.workshopmongo.services.exception.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();    
    }
    public User findById(String id){
        Optional<User> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado."));
    }

    public User insert(User obj){
        return userRepository.insert(obj);
    }

    public void delete(String id){
        findById(id);
        userRepository.deleteById(id);
    }
    public User update (User obj){
        Optional<User> newObj = userRepository.findById(obj.getId());
        User user = newObj.get();
        updateData(user, obj);
        return userRepository.save(user);
    }
    public void updateData(User user, User obj){
        user.setName(obj.getName());
        user.setEmail(obj.getEmail());
    }

    public User fromDTO(UserDTO objDto){
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
    }
}
