package com.datacenter.datateam.domain.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "El campo 'password' es obligatorio")
    private String password;

    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

}
