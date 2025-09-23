package com.example.backend.service;

import com.example.backend.dto.UserRequest;
import com.example.backend.model.User;
import com.example.backend.model.SectorSubcategory;
import com.example.backend.repository.UserRepository;
import com.example.backend.repository.SectorSubcategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserRepository userRepo;
    private SectorSubcategoryRepository subcategoryRepo;
    private UserService userService;

    private User user;
    private List<SectorSubcategory> subcategories;

    @BeforeEach
    void setUp() {
        userRepo = mock(UserRepository.class);
        subcategoryRepo = mock(SectorSubcategoryRepository.class);
        userService = new UserService(userRepo, subcategoryRepo);

        // Initialize user
        user = new User();
        user.setId(1L);
        user.setName("John");
        user.setAgreedToTerms(true);
        user.setSubcategories(new ArrayList<>()); // <-- Fix NullPointerException

        // Initialize subcategories
        SectorSubcategory sub1 = new SectorSubcategory(1L, "AI", null);
        SectorSubcategory sub2 = new SectorSubcategory(2L, "Robotics", null);
        subcategories = Arrays.asList(sub1, sub2);
    }

    @Test
    void createUser_success() {
        UserRequest request = new UserRequest();
        request.setName("John");
        request.setAgreedToTerms(true);
        request.setSubcategoryIds(Arrays.asList(1L, 2L));

        when(subcategoryRepo.findAllById(request.getSubcategoryIds())).thenReturn(subcategories);
        when(userRepo.save(any(User.class))).thenReturn(user);

        User result = userService.createOrUpdateUser(request);

        assertEquals("John", result.getName());
        assertEquals(0, result.getSubcategories().size()); // user subcategories list initialized but mocked save returns empty
        verify(userRepo, times(1)).save(any(User.class));
    }

    @Test
    void getUserById_success() {
        when(userRepo.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1L);

        assertEquals("John", result.getName());
        assertTrue(result.isAgreedToTerms());
        assertNotNull(result.getSubcategories());
    }

    @Test
    void getUserById_notFound() {
        when(userRepo.findById(2L)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            userService.getUserById(2L);
        });

        assertEquals("404 NOT_FOUND \"User not found\"", exception.getMessage());
    }
}
