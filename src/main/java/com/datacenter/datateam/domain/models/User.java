package com.datacenter.datateam.domain.models;

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

    @ManyToOne
    @JoinColumn(name = "document_type_id")
    private Parameter documentType;


    @Column(name = "document_number", nullable = false, unique = true)
    private String documentNumber;

    @ManyToOne
    @JoinColumn(name = "document_issue_city_id", nullable = false)
    private City documentIssueCity;

    @ManyToOne
    @JoinColumn(name = "nationality_id", nullable = false)
    private Nationality nationality;

    @ManyToOne
    @JoinColumn(name = "marital_status_id", nullable = false)
    private Parameter maritalStatus;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @ManyToMany
    @JoinTable(
        name = "users_roles", schema = "private",
        joinColumns = @JoinColumn(name = "users_id"),
        inverseJoinColumns = @JoinColumn(name = "roles_id")
    )
    private List<Role> roles;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private UserStatus status;
}
