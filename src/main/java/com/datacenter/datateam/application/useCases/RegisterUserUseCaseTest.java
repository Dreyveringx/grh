package com.datacenter.datateam.application.useCases;

 
import com.datacenter.datateam.domain.models.User;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.RegisterUserRequest;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.responses.UserResponse;
import com.datacenter.datateam.infrastructure.mappers.UserMapper;
import com.datacenter.datateam.infrastructure.ports.out.UserOutputPort;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
 
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
        request.setEmail("test@example.com");
        request.setPassword("123456");
 
        User user = new User();
        user.setEmail(request.getEmail());
 
        UserResponse userResponse = new UserResponse(1L, "First", "Last", "test@example.com", "123456789", "ID", "Country", "Single", "Company", "Developer", "Active", null);
 
        when(userOutputPort.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(userMapper.toUser(request)).thenReturn(user);
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
        when(userOutputPort.save(user)).thenReturn(user);
        when(userMapper.toResponse(user)).thenReturn(userResponse);
 
        // Act
        UserResponse response = registerUserUseCase.execute(request);
 
        // Assert
        assertNotNull(response);
        assertEquals(response.getEmail(), "test@example.com");
        verify(userOutputPort, times(1)).save(user); // Verifica que se haya llamado al método de save
    }
 
    @test
    void testRegisterUserEmailAlreadyExists() {
        // Arrange
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail("test@example.com");
 
        when(userOutputPort.findByEmail(request.getEmail())).thenReturn(Optional.of(new User()));
 
        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            registerUserUseCase.execute(request);
        });
 
        assertEquals("El email ya está registrado.", exception.getMessage());
    }
}
