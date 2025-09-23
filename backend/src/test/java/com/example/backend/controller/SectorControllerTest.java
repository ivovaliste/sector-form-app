package com.example.backend.controller;

import com.example.backend.model.SectorCategory;
import com.example.backend.model.SectorSubcategory;
import com.example.backend.service.SectorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SectorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SectorService sectorService;

    private List<SectorCategory> categories;

    @BeforeEach
    void setUp() {
        // Create mock category and subcategory
        SectorCategory manufacturing = new SectorCategory(1L, "Manufacturing");
        SectorSubcategory sub1 = new SectorSubcategory(1L, "Construction materials", manufacturing);
        SectorSubcategory sub2 = new SectorSubcategory(2L, "Electronics and Optics", manufacturing);

        manufacturing.setSubcategories(Arrays.asList(sub1, sub2));

        categories = Arrays.asList(manufacturing);
    }

    @Test
    void testGetCategoriesWithSubcategories() throws Exception {
        // Mock the service to return categories
        when(sectorService.getAllCategoriesWithSubcategories()).thenReturn(categories);

        mockMvc.perform(get("/api/sectors"))
                .andExpect(status().isOk())
                // Check main category DTO
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Manufacturing"))
                // Check first subcategory DTO
                .andExpect(jsonPath("$[0].subcategories[0].id").value(1))
                .andExpect(jsonPath("$[0].subcategories[0].name").value("Construction materials"))
                // Check second subcategory DTO
                .andExpect(jsonPath("$[0].subcategories[1].id").value(2))
                .andExpect(jsonPath("$[0].subcategories[1].name").value("Electronics and Optics"));
    }
}
