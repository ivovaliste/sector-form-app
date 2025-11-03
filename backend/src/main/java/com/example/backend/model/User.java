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

    // --- Many-to-Many with Sector (single table) ---
    @ManyToMany
    @JoinTable(
            name = "user_sectors",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "sector_id")
    )
    private List<Sector> sectors = new ArrayList<>();

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

    public List<Sector> getSectors() { return sectors; }
    public void setSectors(List<Sector> sectors) {
        this.sectors = sectors != null ? sectors : new ArrayList<>();
    }
}
