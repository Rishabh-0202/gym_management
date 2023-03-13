package com.example.pokemon.controller;

import com.example.pokemon.model.User;
import com.example.pokemon.repository.UserRepository;
import com.example.pokemon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;
    private UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }


    @GetMapping("/api/v1/getAllUsers")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> getListOfUsers(){
        return userRepository.findAll();
    }

    @RequestMapping("/api/v1/wantMembership/{username}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> wantMembership(@PathVariable String username){
        User user;
        user = userRepository.findUserByUsername(username);
        if(user!=null){
            user.setMembership(true);
            userService.updateUser(user);
            return new ResponseEntity<>("Successfully updated membership", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Username not found",HttpStatus.BAD_REQUEST);
        }

    }
}
