package com.tilde.userportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tilde.userportal.model.User;
import com.tilde.userportal.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Integer> createUser(@RequestBody User user) {
        int userId = userService.registerUser(user);
        return new ResponseEntity<>(userId, HttpStatus.CREATED);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        User user = userService.getUserById(id);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();

        if (users == null || users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(users);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user) {
        // Set the id from the url into the request body
        user.setId(id);

        // Calling service
        boolean updatedUser = userService.updateUser(user);

        // If user not found
        if (!updatedUser) {
            return ResponseEntity.notFound().build();
        }

        // if user found
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") int id) {
        boolean userDeleted = userService.deleteUserById(id);

        if (!userDeleted) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.noContent().build();
    }
}
