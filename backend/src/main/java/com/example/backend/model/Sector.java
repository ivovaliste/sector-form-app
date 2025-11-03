package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sector")
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // ---- Parent (self-reference) ----
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    private Sector parent;

    // ---- Children (self-reference) ----
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Sector> children = new ArrayList<>();

    // ---- Constructors ----
    public Sector() {}

    public Sector(String name) {
        this.name = name;
    }

    public Sector(String name, Sector parent) {
        this.name = name;
        this.parent = parent;
    }

    // ---- Getters & Setters ----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Sector getParent() { return parent; }
    public void setParent(Sector parent) { this.parent = parent; }

    public List<Sector> getChildren() { return children; }
    public void setChildren(List<Sector> children) { this.children = children; }
}
