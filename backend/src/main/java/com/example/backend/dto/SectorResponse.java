package com.example.backend.dto;

import java.util.List;

public class SectorResponse {
    private Long id;
    private String name;
    private List<SectorResponse> children; // recursive list for sub-sectors

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<SectorResponse> getChildren() { return children; }
    public void setChildren(List<SectorResponse> children) { this.children = children; }
}
