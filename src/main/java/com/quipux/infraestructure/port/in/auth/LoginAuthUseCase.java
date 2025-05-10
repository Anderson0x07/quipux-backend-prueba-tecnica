package com.quipux.infraestructure.port.in.auth;

import com.quipux.domain.UserComplete;
import com.quipux.domain.UserLogin;

public interface LoginAuthUseCase {
    UserComplete execute(UserLogin userLogin);
}
