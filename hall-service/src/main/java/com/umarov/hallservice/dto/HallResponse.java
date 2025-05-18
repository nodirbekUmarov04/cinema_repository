package com.umarov.hallservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HallResponse {
    private Long id;
    private String name;
    private int totalRows;
    private int seatsPerRow;
}
