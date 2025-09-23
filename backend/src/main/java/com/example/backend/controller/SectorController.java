package com.example.backend.controller;

import com.example.backend.dto.SectorCategoryResponse;
import com.example.backend.dto.SectorSubcategoryResponse;
import com.example.backend.model.SectorCategory;
import com.example.backend.model.SectorSubcategory;
import com.example.backend.service.SectorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sectors")
@CrossOrigin(origins = "*")
public class SectorController {

    private final SectorService sectorService;

    public SectorController(SectorService sectorService) {
        this.sectorService = sectorService;
    }

    @GetMapping
    public List<SectorCategoryResponse> getCategoriesWithSubcategories() {
        return sectorService.getAllCategoriesWithSubcategories()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private SectorCategoryResponse toDto(SectorCategory category) {
        SectorCategoryResponse dto = new SectorCategoryResponse();
        dto.setId(category.getId());
        dto.setName(category.getName());

        List<SectorSubcategoryResponse> subDtos = category.getSubcategories()
                .stream()
                .map(this::toSubDto)
                .collect(Collectors.toList());

        dto.setSubcategories(subDtos);
        return dto;
    }

    private SectorSubcategoryResponse toSubDto(SectorSubcategory subcategory) {
        SectorSubcategoryResponse dto = new SectorSubcategoryResponse();
        dto.setId(subcategory.getId());
        dto.setName(subcategory.getName());
        return dto;
    }
}
