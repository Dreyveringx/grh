package com.datacenter.datateam.domain.models;

import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "parameters", schema = "private")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "value")
    private String value;

    @Column(name = "description")
    private String description;
}


