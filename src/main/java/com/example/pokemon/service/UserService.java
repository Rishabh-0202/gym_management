package com.example.pokemon.service;

import com.example.pokemon.model.User;
import com.example.pokemon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAllUserByAdmin(){
        return userRepository.findByRoles("USER");
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }
}
