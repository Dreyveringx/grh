package com.datacenter.datateam.domain.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_tipo_documento", nullable = false)
    private Integer idTipoDocumento;

    @Column(unique = true, nullable = false)
    private String nuip;


    @Column(nullable = false)
    private String Password;


public void setPassword(String Password) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    this.Password = passwordEncoder.encode(Password);
}

}

