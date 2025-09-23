package com.example.backend.controller;

import com.example.backend.dto.UserRequest;
import com.example.backend.dto.UserResponse;
import com.example.backend.dto.SectorSubcategoryResponse;
import com.example.backend.model.User;
import com.example.backend.model.SectorSubcategory;
import com.example.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponse createOrUpdateUser(@RequestBody UserRequest request) {
        User user = userService.createOrUpdateUser(request);
        return toDto(user);
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return toDto(user);
    }

    private UserResponse toDto(User user) {
        UserResponse dto = new UserResponse();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setAgreedToTerms(user.isAgreedToTerms());
        List<SectorSubcategoryResponse> subDtos = user.getSubcategories().stream()
                .map(sub -> {
                    SectorSubcategoryResponse subDto = new SectorSubcategoryResponse();
                    subDto.setId(sub.getId());
                    subDto.setName(sub.getName());
                    return subDto;
                })
                .collect(Collectors.toList());
        dto.setSubcategories(subDtos);
        return dto;
    }
}
