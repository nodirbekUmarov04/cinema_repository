package com.umarov.showtimeservice.dto;

import lombok.Data;

@Data
public class HallResponse {
    private Long id;
    private String name;
    private int totalRows;
    private int seatsPerRow;
}
