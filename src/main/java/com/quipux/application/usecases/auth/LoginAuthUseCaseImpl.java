package com.quipux.application.usecases.auth;

import com.quipux.domain.UserComplete;
import com.quipux.domain.UserLogin;
import com.quipux.infraestructure.port.in.auth.LoginAuthUseCase;
import com.quipux.infraestructure.port.out.AuthPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LoginAuthUseCaseImpl implements LoginAuthUseCase {

    private final AuthPort authPort;

    @Override
    public UserComplete execute(UserLogin userLogin) {
        return authPort.login(userLogin);
    }
}
