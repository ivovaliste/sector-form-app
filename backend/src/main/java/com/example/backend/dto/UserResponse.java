package com.example.backend.dto;

import java.util.List;

public class UserResponse {
    private Long id;
    private String name;
    private boolean agreedToTerms;
    private List<Long> sectorIds;  // <- only IDs

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public boolean isAgreedToTerms() { return agreedToTerms; }
    public void setAgreedToTerms(boolean agreedToTerms) { this.agreedToTerms = agreedToTerms; }

    public List<Long> getSectorIds() { return sectorIds; }
    public void setSectorIds(List<Long> sectorIds) { this.sectorIds = sectorIds; }
}
