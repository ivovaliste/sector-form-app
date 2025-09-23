package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "sector_category")
public class SectorCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<SectorSubcategory> subcategories;

    public SectorCategory() {}

    public SectorCategory(String name) {
        this.name = name;
    }

    public SectorCategory(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<SectorSubcategory> getSubcategories() { return subcategories; }
    public void setSubcategories(List<SectorSubcategory> subcategories) { this.subcategories = subcategories; }
}
