package com.quipux.infraestructure.port.in.auth;

import com.quipux.domain.User;
import com.quipux.domain.UserComplete;
import com.quipux.domain.UserLogin;

public interface RegisterUseCase {
    String execute(User user);
}
