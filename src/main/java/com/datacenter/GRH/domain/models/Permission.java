package com.datacenter.GRH.domain.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "permissions", schema = "private")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;
}
