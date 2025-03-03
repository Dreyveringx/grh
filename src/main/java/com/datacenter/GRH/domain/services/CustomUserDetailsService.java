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
            User user = userRepository.findByDocumentNumber(documentNumber)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con documento: " + documentNumber));
    
            // Convertimos roles y permisos a autoridades de Spring Security
            List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .flatMap(role -> {
                    List<SimpleGrantedAuthority> roleAuthorities = role.getPermissions().stream()
                            .map(permission -> new SimpleGrantedAuthority("PERMISSION_" + permission.getName()))
                            .collect(Collectors.toList());
                    roleAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
                    return roleAuthorities.stream();
                })
                .collect(Collectors.toList());
    
            return new org.springframework.security.core.userdetails.User(
                    user.getDocumentNumber(),
                    user.getPassword(),
                    authorities
            );
        }
    }
    
