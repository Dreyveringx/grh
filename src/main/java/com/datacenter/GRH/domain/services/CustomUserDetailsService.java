    package com.datacenter.GRH.domain.services;

    import lombok.RequiredArgsConstructor;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.stereotype.Service;

import com.datacenter.GRH.domain.models.User;
import com.datacenter.GRH.infrastructure.adapters.out.databases.UserRepository;

import java.util.List;
    import java.util.stream.Collectors;

    @Service
    @RequiredArgsConstructor
    public class CustomUserDetailsService implements UserDetailsService {

        private final UserRepository userRepository;

        @Override
        public UserDetails loadUserByUsername(String documentNumber) throws UsernameNotFoundException {
            // Buscar el usuario en la base de datos usando documentNumber
            User user = userRepository.findByDocumentNumber(documentNumber)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con documento: " + documentNumber));

            // Convertir los roles a una lista de SimpleGrantedAuthority
            List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())) // Prefijo "ROLE_"
                .collect(Collectors.toList());

            // Crear un UserDetails con la información del usuario
            return org.springframework.security.core.userdetails.User
                .withUsername(user.getDocumentNumber()) // ✅ Usamos el número de documento en lugar del email
                .password(user.getPassword())
                .authorities(authorities)
                .build();
        }
    }
