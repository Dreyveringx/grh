package com.datacenter.GRH.infrastructure.adapters.out.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
protected void doFilterInternal(@NonNull HttpServletRequest request,
                                @NonNull HttpServletResponse response,
                                @NonNull FilterChain filterChain)
        throws ServletException, IOException {

    final String authorizationHeader = request.getHeader("Authorization");

    if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
        filterChain.doFilter(request, response); // ✅ Continuar la ejecución si no hay token
        return;
    }

    try {
        String jwt = authorizationHeader.substring(7);
        System.out.println("✅ Token recibido: " + jwt);

        String documentNumber = jwtUtil.extractDocumentNumber(jwt);
        List<String> roles = jwtUtil.extractRoles(jwt);

        System.out.println("📌 DocumentNumber extraído: " + documentNumber);
        System.out.println("📌 Roles extraídos: " + roles);

        if (documentNumber != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            List<SimpleGrantedAuthority> authorities = roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(documentNumber, null, authorities);
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authToken);
            System.out.println("✅ Usuario autenticado con roles: " + authorities);
        }
    } catch (Exception e) {
        System.err.println("⚠️ Error en la validación del token JWT: " + e.getMessage());
    }

    filterChain.doFilter(request, response); // ✅ Asegurar que la solicitud continúe
}
}