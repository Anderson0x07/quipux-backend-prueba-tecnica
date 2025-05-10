package com.quipux.application.usecases.auth;

import com.quipux.domain.User;
import com.quipux.domain.UserComplete;
import com.quipux.domain.UserLogin;
import com.quipux.infraestructure.port.in.auth.LoginAuthUseCase;
import com.quipux.infraestructure.port.in.auth.RegisterUseCase;
import com.quipux.infraestructure.port.out.AuthPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RegisterUseCaseImpl implements RegisterUseCase {

    private final AuthPort authPort;

    @Override
    public String execute(User user) {
        return authPort.register(user);
    }
}
