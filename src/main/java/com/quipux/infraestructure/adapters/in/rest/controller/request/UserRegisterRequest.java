package com.quipux.infraestructure.adapters.in.rest.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterRequest {

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String completeName;
}
