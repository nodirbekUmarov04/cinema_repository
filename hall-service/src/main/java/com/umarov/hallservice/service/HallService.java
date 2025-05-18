package com.umarov.hallservice.service;

import com.umarov.hallservice.dto.HallAddRequest;
import com.umarov.hallservice.dto.HallResponse;
import com.umarov.hallservice.entity.Hall;
import com.umarov.hallservice.repository.HallRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HallService {
    private final HallRepository hallRepository;

    public void addHall(HallAddRequest request) {
        hallRepository.save(toHall(request));
    }

    public HallResponse getHallById(Long id) {
        if (!hallRepository.existsHallById(id)) {
            throw new RuntimeException("Hall not found");
        }
        return toHallResponse(hallRepository.getHallById(id));
    }

    Hall toHall(HallAddRequest request) {
        return Hall.builder()
                .name(request.getName())
                .totalRows(request.getTotalRows())
                .seatsPerRow(request.getSeatsPerRow())
                .build();
    }

    HallResponse toHallResponse(Hall hall) {
        return HallResponse.builder()
                .id(hall.getId())
                .name(hall.getName())
                .totalRows(hall.getTotalRows())
                .seatsPerRow(hall.getSeatsPerRow())
                .build();
    }

}
