package com.umarov.userservice.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "role_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}