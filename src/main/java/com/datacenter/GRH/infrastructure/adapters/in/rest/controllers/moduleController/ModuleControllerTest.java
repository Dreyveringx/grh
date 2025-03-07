package com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.moduleController;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.datacenter.GRH.application.useCases.module.CreateModuleUseCase;
import com.datacenter.GRH.application.useCases.module.DeleteModuleUseCase;
import com.datacenter.GRH.application.useCases.module.FindAllModulesUseCase;
import com.datacenter.GRH.application.useCases.module.FindModuleByIdUseCase;
import com.datacenter.GRH.application.useCases.module.UpdateModuleUseCase;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.requests.ModuleRequest;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.responses.ModuleResponse;

@ExtendWith(MockitoExtension.class)
class ModuleControllerTest {

    @Mock
    private CreateModuleUseCase createModuleUseCase;

    @Mock
    private FindAllModulesUseCase findAllModulesUseCase;

    @Mock
    private FindModuleByIdUseCase findModuleByIdUseCase;

    @Mock
    private UpdateModuleUseCase updateModuleUseCase;

    @Mock
    private DeleteModuleUseCase deleteModuleUseCase;

    @InjectMocks
    private ModuleController moduleController; // No se usa MockMvc, se usa el controlador directamente

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateModule() {
        // Arrange
        ModuleRequest request = new ModuleRequest();
        request.setName("Nuevo Módulo");
        request.setDescription("Descripción del módulo");

        ModuleResponse expectedResponse = new ModuleResponse(1L, "Nuevo Módulo", "Descripción del módulo");
        when(createModuleUseCase.execute(any(ModuleRequest.class))).thenReturn(expectedResponse);

        // Act
        ModuleResponse actualResponse = moduleController.createModule(request).getBody();

        // Assert
        assertNotNull(actualResponse);
        assertEquals("Nuevo Módulo", actualResponse.getName());
    }

    @Test
    void testGetAllModules() {
        // Arrange
        List<ModuleResponse> modules = Arrays.asList(
                new ModuleResponse(1L, "Módulo 1", "Descripción 1"),
                new ModuleResponse(2L, "Módulo 2", "Descripción 2")
        );

        when(findAllModulesUseCase.execute()).thenReturn(modules);

        // Act
        List<ModuleResponse> actualModules = moduleController.getAllModules().getBody();

        // Assert
        assertNotNull(actualModules);
        assertEquals(2, actualModules.size());
    }

    @Test
    void testGetModuleById() {
        // Arrange
        ModuleResponse expectedResponse = new ModuleResponse(1L, "Módulo 1", "Descripción");
        when(findModuleByIdUseCase.execute(1L)).thenReturn(expectedResponse);

        // Act
        ModuleResponse actualResponse = moduleController.getModuleById(1L).getBody();

        // Assert
        assertNotNull(actualResponse);
        assertEquals("Módulo 1", actualResponse.getName());
    }

    @Test
    void testUpdateModule() {
        // Arrange
        ModuleRequest request = new ModuleRequest();
        request.setName("Módulo Actualizado");
        request.setDescription("Descripción Actualizada");

        ModuleResponse expectedResponse = new ModuleResponse(1L, "Módulo Actualizado", "Descripción Actualizada");
        when(updateModuleUseCase.execute(eq(1L), any(ModuleRequest.class))).thenReturn(expectedResponse);

        // Act
        ModuleResponse actualResponse = moduleController.updateModule(1L, request).getBody();

        // Assert
        assertNotNull(actualResponse);
        assertEquals("Módulo Actualizado", actualResponse.getName());
    }

    @Test
    void testDeleteModule() {
        // Arrange
        doNothing().when(deleteModuleUseCase).execute(1L);

        // Act
        assertDoesNotThrow(() -> moduleController.deleteModule(1L));
    }
}
