package com.example.backend.controller;

import com.example.backend.model.SectorCategory;
import com.example.backend.repository.SectorCategoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sectors")
@CrossOrigin(origins = "*")
public class SectorController {

    private final SectorCategoryRepository categoryRepo;

    public SectorController(SectorCategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    /**
     * Returns all categories with their subcategories nested.
     */
    @GetMapping
    public List<SectorCategory> getCategoriesWithSubcategories() {
        return categoryRepo.findAll(); 
    }
}
