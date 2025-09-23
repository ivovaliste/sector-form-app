package com.example.backend.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")  // table name
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean agreedToTerms;

    @ManyToMany
    @JoinTable(
            name = "user_subcategories",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "subcategory_id")
    )
    private List<SectorSubcategory> subcategories = new ArrayList<>(); // <- initialized here

    // --- Constructors ---

    public User() {}

    public User(String name, boolean agreedToTerms) {
        this.name = name;
        this.agreedToTerms = agreedToTerms;
    }

    public User(Long id, String name, boolean agreedToTerms) {
        this.id = id;
        this.name = name;
        this.agreedToTerms = agreedToTerms;
    }

    // --- Getters & Setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public boolean isAgreedToTerms() { return agreedToTerms; }
    public void setAgreedToTerms(boolean agreedToTerms) { this.agreedToTerms = agreedToTerms; }

    public List<SectorSubcategory> getSubcategories() { return subcategories; }
    public void setSubcategories(List<SectorSubcategory> subcategories) { 
        this.subcategories = subcategories != null ? subcategories : new ArrayList<>();
    }
}
