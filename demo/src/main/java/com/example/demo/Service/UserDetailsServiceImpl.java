package com.example.demo.Service;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Looking for user: " + username);
        User user = userRepository.findByUsername(username);

        if (user != null) {
            System.out.println("User found: " + user.getUsername() + ", password length: " + user.getPassword().length());
            String[] roles = user.getRoles() != null ? user.getRoles().toArray(new String[0]) : new String[]{"USER"};
            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(roles)
                    .build();

            return userDetails;
        }
        System.out.println("User not found: " + username);
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
