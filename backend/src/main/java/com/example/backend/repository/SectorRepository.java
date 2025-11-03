package com.example.backend.repository;

import com.example.backend.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {

    // Fetch all top-level sectors (no parent)
    List<Sector> findByParentIsNull();

    // Fetch all children of a specific parent sector
    List<Sector> findByParentId(Long parentId);
}
