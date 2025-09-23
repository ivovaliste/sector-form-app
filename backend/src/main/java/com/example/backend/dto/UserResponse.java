package com.example.backend.dto;

import java.util.List;

public class UserResponse {
    private Long id;
    private String name;
    private boolean agreedToTerms;
    private List<SectorSubcategoryResponse> subcategories;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public boolean isAgreedToTerms() { return agreedToTerms; }
    public void setAgreedToTerms(boolean agreedToTerms) { this.agreedToTerms = agreedToTerms; }

    public List<SectorSubcategoryResponse> getSubcategories() { return subcategories; }
    public void setSubcategories(List<SectorSubcategoryResponse> subcategories) { this.subcategories = subcategories; }
}
