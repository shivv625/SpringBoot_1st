package com.example.demo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.Entity.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
    // If you want to delete by username, use the following method:
    void deleteByUsername(String username);
}
