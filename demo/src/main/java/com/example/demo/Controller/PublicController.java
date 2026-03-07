package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public ResponseEntity<String> healthCheck() {
        return new ResponseEntity<>("OK - Application is running", HttpStatus.OK);
    }

    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        boolean saved = userService.saveNewUser(user);
        if (saved) {
            return new ResponseEntity<>("User created successfully: " + user.getUsername(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create user. Username may already exist.", HttpStatus.CONFLICT);
        }
    }


    @DeleteMapping("/delete-all-users")
    public ResponseEntity<String> deleteAllUsers() {
        userService.deleteAll();
        return new ResponseEntity<>("All users deleted successfully.", HttpStatus.OK);
    }
}
