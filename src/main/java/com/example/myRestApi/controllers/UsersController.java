package com.example.myRestApi.controllers;

import com.example.myRestApi.user.User;
import com.example.myRestApi.user.UserDaoService;
import com.example.myRestApi.exceptions.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UserDaoService userService;

    public UsersController(UserDaoService userDaoService) {
        super();
        this.userService = userDaoService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id) {
        User user = userService.findOne(id);

        if (user == null) {
            throw new UserNotFoundException("id: " + id);
        }

        return user;
    }

    @PostMapping("/users")
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        userService.create(user);
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.setLocation(URI.create("/users/" + user.getId()));
//        responseHeader.set("Location", "/users/" + user.getId());
//        responseHeader.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<User>(user, responseHeader, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/users/{id}")
    public boolean deleteUser(@PathVariable int id) {
        return userService.deleteOne(id);
    }
}
