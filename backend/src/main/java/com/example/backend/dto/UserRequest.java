package com.example.backend.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class UserRequest {

    private Long id; 

    @NotEmpty(message = "Name is required")
    private String name;

    @NotNull(message = "Subcategories cannot be null")
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
