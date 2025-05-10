package com.quipux.infraestructure.adapters.in.rest.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginRequest {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
