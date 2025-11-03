package com.example.backend.controller;

import com.example.backend.dto.UserRequest;
import com.example.backend.dto.UserResponse;
import com.example.backend.dto.SectorResponse;
import com.example.backend.model.User;
import com.example.backend.model.Sector;
import com.example.backend.service.UserService;
import com.example.backend.service.SectorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;
    private final SectorService sectorService;

    public UserController(UserService userService, SectorService sectorService) {
        this.userService = userService;
        this.sectorService = sectorService;
    }


    @PostMapping
    public UserResponse createOrUpdateUser(@Valid @RequestBody UserRequest request) {
        // Fetch Sector entities based on sectorIds
        List<Sector> sectors = sectorService.getSectorsByIds(request.getSectorIds());
        User user = userService.createOrUpdateUser(request, sectors);
        return toDto(user);
    }


    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return toDto(user);
    }

    // 🔹 Convert User entity to DTO
    private UserResponse toDto(User user) {
        UserResponse dto = new UserResponse();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setAgreedToTerms(user.isAgreedToTerms());

        List<Long> sectorIds = user.getSectors()
                .stream()
                .map(Sector::getId)
                .toList();

        dto.setSectorIds(sectorIds);

        return dto;
    }

    // 🔹 Convert Sector entity to DTO
    private SectorResponse toSectorDto(Sector sector) {
        SectorResponse dto = new SectorResponse();
        dto.setId(sector.getId());
        dto.setName(sector.getName());

        if (sector.getChildren() != null && !sector.getChildren().isEmpty()) {
            List<SectorResponse> childrenDtos = sector.getChildren().stream()
                    .map(this::toSectorDto)
                    .collect(Collectors.toList());
            dto.setChildren(childrenDtos);
        }

        return dto;
    }
}
