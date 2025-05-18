package com.umarov.hallservice.controller;

import com.umarov.hallservice.dto.HallAddRequest;
import com.umarov.hallservice.dto.HallResponse;
import com.umarov.hallservice.service.HallService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hall")
@AllArgsConstructor
public class HallController {
    private final HallService hallService;

    @PostMapping("/")
    public ResponseEntity<String> addHall(@RequestBody HallAddRequest request) {
        hallService.addHall(request);
        return ResponseEntity.ok("Hall added successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<HallResponse> getHallById(@PathVariable Long id) {
        return ResponseEntity.ok(hallService.getHallById(id));
    }
}
