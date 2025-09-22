package com.example.backend.controller;

import com.example.backend.model.User;
import com.example.backend.model.SectorSubcategory;
import com.example.backend.repository.UserRepository;
import com.example.backend.repository.SectorSubcategoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserRepository userRepo;
    private final SectorSubcategoryRepository subcategoryRepo;

    public UserController(UserRepository userRepo, SectorSubcategoryRepository subcategoryRepo) {
        this.userRepo = userRepo;
        this.subcategoryRepo = subcategoryRepo;
    }


@PostMapping
public User createOrUpdateUser(@RequestBody UserRequest request) {
    User user;

    if (request.getId() != null) {
        // Update existing user
        user = userRepo.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getId()));
    } else {
        // Create new user
        user = new User();
    }

    user.setName(request.getName());
    user.setAgreedToTerms(request.isAgreedToTerms());

    List<SectorSubcategory> subcategories = subcategoryRepo.findAllById(request.getSubcategoryIds());
    user.setSubcategories(subcategories);

    return userRepo.save(user);
}

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

public static class UserRequest {
    private Long id;  // optional for updates
    private String name;
    private List<Long> subcategoryIds;
    private boolean agreedToTerms;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Long> getSubcategoryIds() { return subcategoryIds; }
    public void setSubcategoryIds(List<Long> subcategoryIds) { this.subcategoryIds = subcategoryIds; }
    public boolean isAgreedToTerms() { return agreedToTerms; }
    public void setAgreedToTerms(boolean agreedToTerms) { this.agreedToTerms = agreedToTerms; }
}
}
