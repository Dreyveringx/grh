package com.datacenter.GRH.domain.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "companies", schema = "private")
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "company_name", nullable = false, unique = true)
    private String companyName;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, unique = true)
    private String nit;

    // Crear entidad attachments
    /*@Column(name = "rut_id", nullable = false)
    private String rutId;*/

    @Column(nullable = false)
    private String email;

    @Column(name = "email_2", nullable = false)
    private String email2;

    private String phone;
    private String address;

    @Column(name = "is_active")
    private Boolean isActive = true;

    private String logo;
    private String image;

    @Column(name = "theme_color")
    private String themeColor;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
}

