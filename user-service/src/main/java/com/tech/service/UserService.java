package com.tech.service;

import com.tech.entity.ApplicationUser;
import com.tech.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<ApplicationUser> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<ApplicationUser> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public ApplicationUser createUser(ApplicationUser user) {
        return userRepository.save(user);
    }

    public ApplicationUser updateUser(Long id, ApplicationUser user) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            return userRepository.save(user);
        } else {
            // Handle the case where the user does not exist
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            // Handle the case where the user does not exist
            throw new RuntimeException("User not found with id: " + id);
        }
    }
}
