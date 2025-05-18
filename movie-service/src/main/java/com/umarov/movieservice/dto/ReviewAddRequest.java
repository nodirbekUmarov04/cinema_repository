package com.umarov.movieservice.dto;

import lombok.Data;

@Data
public class ReviewAddRequest {
    private Long userId;
    private String review;
}
