package com.quipux.infraestructure.adapters.in.rest.controller.response;


import lombok.*;

@Getter
@Setter
public class UserCompleteResponse {
    private String id;

    private String email;

    private String token;

    private String completeName;

}
