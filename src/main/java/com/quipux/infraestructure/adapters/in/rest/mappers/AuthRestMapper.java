package com.quipux.infraestructure.adapters.in.rest.mappers;

import com.quipux.domain.User;
import com.quipux.domain.UserComplete;
import com.quipux.domain.UserLogin;
import com.quipux.infraestructure.adapters.in.rest.controller.request.UserLoginRequest;
import com.quipux.infraestructure.adapters.in.rest.controller.request.UserRegisterRequest;
import com.quipux.infraestructure.adapters.in.rest.controller.response.UserCompleteResponse;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface AuthRestMapper {
    UserLogin userToModel(UserLoginRequest request);

    User requestToDomain(UserRegisterRequest request);

    UserCompleteResponse userCompleteToResponse(UserComplete response);
}
