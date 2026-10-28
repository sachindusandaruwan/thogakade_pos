
package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String username, String password) {

        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username is required");
        }

        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException(
                    "Password must contain at least 8 characters");
        }

        if (userRepository.findByUsername(username.trim()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        User user = new User(
                username.trim(),
                passwordEncoder.encode(password)
        );

        return userRepository.save(user);
    }

    public boolean loginUser(String username, String password) {

        if (username == null || password == null) {
            return false;
        }

        User user = userRepository.findByUsername(username.trim())
                .orElse(null);

        if (user == null) {
            return false;
        }

        return passwordEncoder.matches(password, user.getPassword());
    }
}
