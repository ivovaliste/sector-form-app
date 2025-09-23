package com.example.backend.service;

import com.example.backend.model.SectorCategory;
import com.example.backend.repository.SectorCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectorService {

    private final SectorCategoryRepository categoryRepo;

    public SectorService(SectorCategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public List<SectorCategory> getAllCategoriesWithSubcategories() {
        return categoryRepo.findAll();
    }
}
