package com.quipux.infraestructure.adapters.in.rest.configuration;

import com.quipux.infraestructure.adapters.in.rest.controller.request.UserLoginRequest;
import com.quipux.infraestructure.adapters.in.rest.controller.request.UserRegisterRequest;
import com.quipux.infraestructure.adapters.in.rest.controller.response.DescriptionResponse;
import com.quipux.infraestructure.adapters.in.rest.controller.response.UserCompleteResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Auth", description = "Api para gestion de usuarios")
@SecurityRequirement(name = "")
public interface AuthApi {
    @Operation(summary = "Iniciar sesion", description = "Iniciar sesion")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Iniciar sesion")})
    ResponseEntity<UserCompleteResponse> login(UserLoginRequest request);

    @Operation(summary = "Registrar usuario", description = "Registrar usuario")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Usuario creado")})
    ResponseEntity<DescriptionResponse> register(UserRegisterRequest request);
}
