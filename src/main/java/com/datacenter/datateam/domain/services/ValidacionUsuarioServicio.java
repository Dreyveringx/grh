package com.datacenter.datateam.domain.services;


import org.springframework.stereotype.Service;

import com.datacenter.datateam.application.exceptions.UsuarioInvalidoException;
import com.datacenter.datateam.domain.models.User;

@Service
public class ValidacionUsuarioServicio {
    public void validarNuevoUsuario(User user) {
        if (user.getNuip() == null || user.getNuip().trim().isEmpty()) {
            throw new UsuarioInvalidoException("El NUIP no puede estar vacío");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new UsuarioInvalidoException("La contraseña no puede estar vacía");
        }
    }
}
