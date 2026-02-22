package com.example.demo.Controller;

import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    public UserService userService;

    @GetMapping
    public ResponseEntity<User> GetAll(){
        userService.getAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Boolean> createUser(@RequestBody User user){
       userService.saveEntry(user);
       return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("id/{myid}")
    public ResponseEntity<User> findByUserName( @PathVariable String myid){
        userService.findByUserName(myid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user , @PathVariable String userName
                                        ) {
        User userInDb = userService.findByUserName(userName);
        if (userInDb != null) {
            userInDb.setUsername(user.getUsername());
            userInDb.setPassword(user.getPassword());
            userService.saveEntry(userInDb);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
