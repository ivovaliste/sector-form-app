package com.example.backend.service;

import com.example.backend.model.SectorCategory;
import com.example.backend.model.SectorSubcategory;
import com.example.backend.repository.SectorCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SectorServiceTest {

    private SectorCategoryRepository categoryRepo;
    private SectorService sectorService;

    private List<SectorCategory> categories;

    @BeforeEach
    void setUp() {
        categoryRepo = mock(SectorCategoryRepository.class);
        sectorService = new SectorService(categoryRepo);

        SectorCategory cat1 = new SectorCategory(1L, "Manufacturing");
        SectorSubcategory sub1 = new SectorSubcategory(1L, "Construction materials", cat1);
        cat1.setSubcategories(Arrays.asList(sub1));

        categories = Arrays.asList(cat1);
    }

    @Test
    void getAllCategoriesWithSubcategories_success() {
        when(categoryRepo.findAll()).thenReturn(categories);

        List<SectorCategory> result = sectorService.getAllCategoriesWithSubcategories();

        assertEquals(1, result.size());
        assertEquals("Manufacturing", result.get(0).getName());
        assertEquals(1, result.get(0).getSubcategories().size());
        verify(categoryRepo, times(1)).findAll();
    }
}
