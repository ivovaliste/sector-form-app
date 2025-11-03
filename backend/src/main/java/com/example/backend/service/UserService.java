package com.example.backend.service;

import com.example.backend.dto.UserRequest;
import com.example.backend.model.Sector;
import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 🔹 Create or update a user
    public User createOrUpdateUser(UserRequest request, List<Sector> sectors) {
        User user;

        if (request.getId() != null) {
            Optional<User> optionalUser = userRepository.findById(request.getId());
            user = optionalUser.orElse(new User());
        } else {
            user = new User();
        }

        user.setName(request.getName());
        user.setAgreedToTerms(request.isAgreedToTerms());
        user.setSectors(sectors);  // set selected sectors

        return userRepository.save(user);
    }

    // 🔹 Get user by ID
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
}
