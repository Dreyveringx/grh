package com.datacenter.datateam.domain.models;


import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firsName;
    private String lastName;
    private Integer documentTypeId;
    private String documetNumber;
    private Integer documentFileId;
    private LocalDate birtheyDate;
    private LocalDate documentIssueDate;
    private Integer documentIssueCity;
    private Integer nationalityId;
    private Integer maritalStatusId;
    private boolean hasHouse;
    private boolean hasChildren;
    private String email;
    private String email2;
    private String phone;
    private Integer companyId;
    private Integer positionId;
    private boolean isActive;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
    private Integer statusId;
    private String password;
}
