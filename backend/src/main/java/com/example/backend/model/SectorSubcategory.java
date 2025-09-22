package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "sector_subcategory")
public class SectorSubcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private SectorCategory category;

    public SectorSubcategory() {}

    public SectorSubcategory(String name, SectorCategory category) {
        this.name = name;
        this.category = category;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public SectorCategory getCategory() { return category; }
    public void setCategory(SectorCategory category) { this.category = category; }
}
