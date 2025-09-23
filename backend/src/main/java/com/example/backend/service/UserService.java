package com.example.backend.service;

import com.example.backend.dto.UserRequest;
import com.example.backend.model.User;
import com.example.backend.model.SectorSubcategory;
import com.example.backend.repository.UserRepository;
import com.example.backend.repository.SectorSubcategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final SectorSubcategoryRepository subcategoryRepo;

    public UserService(UserRepository userRepo, SectorSubcategoryRepository subcategoryRepo) {
        this.userRepo = userRepo;
        this.subcategoryRepo = subcategoryRepo;
    }

    @Transactional
    public User createOrUpdateUser(UserRequest request) {
        User user;

        if (request.getId() != null) {
            user = userRepo.findById(request.getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        } else {
            user = new User();
        }

        user.setName(request.getName());
        user.setAgreedToTerms(request.isAgreedToTerms());

        List<SectorSubcategory> subcategories = subcategoryRepo.findAllById(request.getSubcategoryIds());
        user.setSubcategories(subcategories);

        return userRepo.save(user);
    }

    public User getUserById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
}
