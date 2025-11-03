package com.example.backend.controller;

import com.example.backend.dto.SectorResponse;
import com.example.backend.model.Sector;
import com.example.backend.service.SectorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sectors")
@CrossOrigin(origins = "*")
public class SectorController {

    private final SectorService sectorService;

    public SectorController(SectorService sectorService) {
        this.sectorService = sectorService;
    }

    @GetMapping("/all")
    public List<SectorResponse> getAllSectors() {
        return sectorService.getAllSectors()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<SectorResponse> getTopLevelSectors() {
        return sectorService.getTopLevelSectors()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }



    // 🔹 Map Entity → DTO recursively
    private SectorResponse toDto(Sector sector) {
        SectorResponse dto = new SectorResponse();
        dto.setId(sector.getId());
        dto.setName(sector.getName());

        if (sector.getChildren() != null && !sector.getChildren().isEmpty()) {
            dto.setChildren(
                    sector.getChildren()
                            .stream()
                            .map(this::toDto)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }
}
