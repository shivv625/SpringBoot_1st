package com.example.demo.Service;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userEntryRepository;

    public User saveEntry(User userEntry){
        return userEntryRepository.save(userEntry);
    }

    public List<User> getAll(){
        return userEntryRepository.findAll();
    }

    public void deleteById(String id){
        userEntryRepository.deleteById(id);
    }
     public User findByUserName(String userName){
        return userEntryRepository.findByUserName(userName);
     }
}
