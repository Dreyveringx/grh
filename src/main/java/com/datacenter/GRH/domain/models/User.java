package com.datacenter.GRH.domain.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users", schema = "private")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @JoinColumn(name = "document_type_id")
    private Integer idDocumentType;

    @ManyToOne
    @JoinColumn(name = "document_type_id", insertable = false, updatable = false)
    private Parameter documentType;


    @Column(name = "document_number", nullable = false, unique = true)
    private String documentNumber;

    //Crear entidad Attachments
    /*@ManyToOne
    @JoinColumn(name = "document_file_id", nullable = false)
    private Attachments documentFile;*/

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "document_issue_date")
    private LocalDate documentIssueDate;

    @ManyToOne
    @JoinColumn(name = "document_issue_city_id", nullable = false)
    private City documentIssueCity;

    @ManyToOne
    @JoinColumn(name = "nationality_id", nullable = false)
    private Nationality nationality;

    @ManyToOne
    @JoinColumn(name = "marital_status_id", nullable = false)
    private Parameter maritalStatus;

    @Column(name= "has_house")
    private Boolean hasHouse;

    @Column(name = "has_children")
    private Boolean hasChildren;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "email_2", unique = true)
    private String email2;

    private String phone;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "status_id")
    private UserStatus status;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(
        name = "users_roles", schema = "private",
        joinColumns = @JoinColumn(name = "users_id"),
        inverseJoinColumns = @JoinColumn(name = "roles_id")
    )
    private List<Role> roles;

    // ✅ Estos campos NO se guardarán en la BD
    @Transient
    private String resetToken;

    @Transient
    private String activationToken;
}
