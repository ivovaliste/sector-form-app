package com.example.backend.controller;

import com.example.backend.dto.UserRequest;
import com.example.backend.model.User;
import com.example.backend.model.SectorSubcategory;
import com.example.backend.repository.UserRepository;
import com.example.backend.repository.SectorSubcategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepo;

    @MockBean
    private SectorSubcategoryRepository subcategoryRepo;

    @Autowired
    private ObjectMapper objectMapper;

    private User user;
    private List<SectorSubcategory> subcategories;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setName("John");
        user.setAgreedToTerms(true);

        SectorSubcategory sub1 = new SectorSubcategory(1L, "AI", null);
        SectorSubcategory sub2 = new SectorSubcategory(2L, "Robotics", null);
        subcategories = Arrays.asList(sub1, sub2);
    }

    @Test
    void testGetUserById() throws Exception {
        when(userRepo.findById(1L)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.agreedToTerms").value(true));
    }

    @Test
    void testCreateUser() throws Exception {
        UserRequest request = new UserRequest();
        request.setName("John");
        request.setAgreedToTerms(true);
        request.setSubcategoryIds(Arrays.asList(1L, 2L));

        when(subcategoryRepo.findAllById(request.getSubcategoryIds())).thenReturn(subcategories);
        when(userRepo.save(org.mockito.ArgumentMatchers.any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.agreedToTerms").value(true));
    }
}
