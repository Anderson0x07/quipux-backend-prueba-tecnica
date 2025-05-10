package com.quipux.infraestructure.port.out;

import com.quipux.domain.User;
import com.quipux.domain.UserComplete;
import com.quipux.domain.UserLogin;

public interface AuthPort {

    UserComplete login(UserLogin userLogin);

    String register(User userLogin);

}
