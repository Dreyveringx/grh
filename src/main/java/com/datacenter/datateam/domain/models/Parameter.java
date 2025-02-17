package com.datacenter.datateam.domain.models;

import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "parameter", schema = "private")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "parameter_parent_id_fkey"), nullable = false)
    private String category;

    private String value;
    private String name;

    @Column(name = "label")
    private String description;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}


