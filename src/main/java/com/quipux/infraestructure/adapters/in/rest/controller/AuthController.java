package com.quipux.infraestructure.adapters.in.rest.controller;

import com.quipux.domain.UserComplete;
import com.quipux.infraestructure.adapters.in.rest.configuration.AuthApi;
import com.quipux.infraestructure.adapters.in.rest.controller.request.UserLoginRequest;
import com.quipux.infraestructure.adapters.in.rest.controller.request.UserRegisterRequest;
import com.quipux.infraestructure.adapters.in.rest.controller.response.DescriptionResponse;
import com.quipux.infraestructure.adapters.in.rest.controller.response.UserCompleteResponse;
import com.quipux.infraestructure.adapters.in.rest.mappers.AuthRestMapper;
import com.quipux.infraestructure.port.in.auth.LoginAuthUseCase;
import com.quipux.infraestructure.port.in.auth.RegisterUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/auth")
@Validated
public class AuthController implements AuthApi {


    private final LoginAuthUseCase loginAuthUseCase;
    private final RegisterUseCase registerUseCase;
    private final AuthRestMapper authRestMapper;


    @Override
    @PostMapping("/login")
    public ResponseEntity<UserCompleteResponse> login(@RequestBody UserLoginRequest request) {
        UserComplete userComplete = loginAuthUseCase.execute(authRestMapper.userToModel(request));
        return new ResponseEntity<>(authRestMapper.userCompleteToResponse(userComplete), HttpStatus.OK);
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity<DescriptionResponse> register(@RequestBody @Valid UserRegisterRequest request) {

        String description = registerUseCase.execute(authRestMapper.requestToDomain(request));

        DescriptionResponse descriptionResponse = new DescriptionResponse();
        descriptionResponse.setDescripcion(description);

        return new ResponseEntity<>(descriptionResponse, HttpStatus.CREATED);
    }

}
