package com.example.backend.service;

import com.example.backend.model.Sector;
import com.example.backend.repository.SectorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectorService {

    private final SectorRepository sectorRepository;

    public SectorService(SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }

    // Get all sectors (including nested ones)
    public List<Sector> getAllSectors() {
        return sectorRepository.findAll();
    }

    // Get only top-level sectors (those without a parent)
    public List<Sector> getTopLevelSectors() {
        return sectorRepository.findByParentIsNull();
    }

    // Get children of a specific sector
    public List<Sector> getSubsectors(Long parentId) {
        return sectorRepository.findByParentId(parentId);
    }

    // Save a new sector (category, subcategory, or deeper)
    public Sector saveSector(Sector sector) {
        return sectorRepository.save(sector);
    }

    // 🔹 Fetch sectors by a list of IDs (new method)
    public List<Sector> getSectorsByIds(List<Long> ids) {
        return sectorRepository.findAllById(ids);
    }
}
