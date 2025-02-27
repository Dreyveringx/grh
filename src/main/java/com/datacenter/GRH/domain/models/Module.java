package com.datacenter.GRH.domain.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "modules", schema = "private")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Module { // 🔥 Se cambia 'Modules' a 'Module' para evitar conflictos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;
}
