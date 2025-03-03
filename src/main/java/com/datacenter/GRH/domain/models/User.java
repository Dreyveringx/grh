package com.datacenter.GRH.domain.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

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

    @ManyToOne
    @JoinColumn(name = "document_type_id", nullable = false)
    private Parameter documentType;

    @Column(name = "document_number", nullable = false, unique = true)
    private String documentNumber;

    // Crear entidad Attachments
    /*
     * @ManyToOne
     * 
     * @JoinColumn(name = "document_file_id", nullable = false)
     * private Attachments documentFile;
     */

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

    @Column(name = "has_house")
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
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private UserStatus status;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER) // 🔥 Evita LazyInitializationException
    @JoinTable(name = "users_roles", schema = "private", joinColumns = @JoinColumn(name = "users_id"), inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roles;

    // ✅ Campos transitorios (NO se guardan en la BD)
    @Transient
    private String resetToken;

    @Transient
    private String activationToken;

    // ✅ Generar timestamps automáticamente
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
