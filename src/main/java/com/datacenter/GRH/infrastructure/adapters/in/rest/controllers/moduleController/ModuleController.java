package com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.moduleController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.datacenter.GRH.application.useCases.module.CreateModuleUseCase;
import com.datacenter.GRH.application.useCases.module.DeleteModuleUseCase;
import com.datacenter.GRH.application.useCases.module.FindAllModulesUseCase;
import com.datacenter.GRH.application.useCases.module.FindModuleByIdUseCase;
import com.datacenter.GRH.application.useCases.module.UpdateModuleUseCase;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.requests.ModuleRequest;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.responses.ModuleResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Módulos", description = "Endpoints para la gestión de módulos")
@RestController
@RequestMapping("/api/modules")
@RequiredArgsConstructor
public class ModuleController {

    private final CreateModuleUseCase createModuleUseCase;
    private final FindAllModulesUseCase findAllModulesUseCase;
    private final FindModuleByIdUseCase findModuleByIdUseCase;
    private final UpdateModuleUseCase updateModuleUseCase;
    private final DeleteModuleUseCase deleteModuleUseCase;

    @Operation(summary = "Crear un módulo", description = "Guarda un nuevo módulo en la base de datos.")
    @ApiResponse(responseCode = "201", description = "Módulo creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @PostMapping
    public ResponseEntity<ModuleResponse> createModule(@RequestBody @Valid ModuleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createModuleUseCase.execute(request));
    }

    @Operation(summary = "Obtener todos los módulos", description = "Devuelve una lista de todos los módulos registrados en el sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de módulos obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<ModuleResponse>> getAllModules() {
        return ResponseEntity.ok(findAllModulesUseCase.execute());
    }

    @Operation(summary = "Obtener un módulo por ID", description = "Devuelve un módulo específico basado en su ID.")
    @ApiResponse(responseCode = "200", description = "Módulo encontrado")
    @ApiResponse(responseCode = "404", description = "Módulo no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<ModuleResponse> getModuleById(@PathVariable Long id) {
        return ResponseEntity.ok(findModuleByIdUseCase.execute(id));
    }

    @Operation(summary = "Actualizar un módulo", description = "Modifica los datos de un módulo existente.")
    @ApiResponse(responseCode = "200", description = "Módulo actualizado correctamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @ApiResponse(responseCode = "404", description = "Módulo no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<ModuleResponse> updateModule(@PathVariable Long id, @RequestBody @Valid ModuleRequest request) {
        return ResponseEntity.ok(updateModuleUseCase.execute(id, request));
    }

    @Operation(summary = "Eliminar un módulo", description = "Elimina un módulo basado en su ID.")
    @ApiResponse(responseCode = "204", description = "Módulo eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "Módulo no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModule(@PathVariable Long id) {
        deleteModuleUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
