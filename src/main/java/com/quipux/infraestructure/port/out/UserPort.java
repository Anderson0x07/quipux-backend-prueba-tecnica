package com.quipux.infraestructure.port.out;

import com.quipux.domain.User;

public interface UserPort {
    User getByEmail(String email);

    User saveUser(User user);
}
