package com.umarov.bookingservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WaitingList {
    @Id
    private Long id;
    
    private Long userId;
    private Long showtimeId;
    private String seat;
}
