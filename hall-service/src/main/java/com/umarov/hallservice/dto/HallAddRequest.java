package com.umarov.hallservice.dto;

import lombok.Data;

@Data
public class HallAddRequest {
    private String name;
    private int totalRows;
    private int seatsPerRow;
}
