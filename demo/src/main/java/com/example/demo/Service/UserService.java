package com.example.demo.Service;

import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userEntryRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private WeatherService weatherService;

    public void saveUser(User user) {
        userEntryRepository.save(user);
    }

    public boolean saveNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userEntryRepository.save(user);
        return true;
    }

    public void saveNewAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER", "ADMIN"));
        userEntryRepository.save(user);
    }

    public List<User> getAll() {
        return userEntryRepository.findAll();
    }

    public void deleteById(String id) {
        userEntryRepository.deleteById(id);
    }

    public void deleteAll() {
        userEntryRepository.deleteAll();
    }

    public User findByUsername(String username) {
        return userEntryRepository.findByUsername(username);
    }

    public WeatherService getWeatherService() {
        return weatherService;
    }
}
