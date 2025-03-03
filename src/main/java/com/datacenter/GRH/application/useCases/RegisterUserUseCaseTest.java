package com.datacenter.GRH.application.useCases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.datacenter.GRH.domain.models.User;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.requests.RegisterUserRequest;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.responses.UserResponse;
import com.datacenter.GRH.infrastructure.mappers.UserMapper;
import com.datacenter.GRH.infrastructure.ports.out.UserOutputPort;

@ExtendWith(MockitoExtension.class)
class RegisterUserUseCaseTest {

    @Mock
    private UserOutputPort userOutputPort;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private RegisterUserUseCase registerUserUseCase;

    @Test
    void testRegisterUserSuccess() {
        // Arrange
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail("");
        request.setDocumentNumber("");
        request.setPassword("");

        User user = new User();
        when(userMapper.toUser(request)).thenReturn(user);
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
        when(userOutputPort.save(user)).thenReturn(user);

        // Act
        UserResponse response = registerUserUseCase.execute(request, null);

        // Assert
        assertNotNull(response);
        assertEquals(response.getEmail(), "test@example.com");
        verify(userOutputPort, times(1)).save(user); // Verifica que se haya llamado al método de save
    }

    @Test
    void testRegisterUserEmailAlreadyExists() {
        // Arrange
        RegisterUserRequest request = new RegisterUserRequest();
        request.setDocumentNumber("");

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            registerUserUseCase.execute(request, null);
        });

        assertEquals("El número de documento ya está registrado.", exception.getMessage());
    }
}
