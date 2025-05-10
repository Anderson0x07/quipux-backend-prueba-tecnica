package com.quipux.infraestructure.adapters.out.adapters;

import com.quipux.application.exceptions.BadCredentialsException;
import com.quipux.application.exceptions.ResourceNotFoundException;
import com.quipux.domain.User;
import com.quipux.domain.UserComplete;
import com.quipux.domain.UserLogin;
import com.quipux.infraestructure.adapters.out.database.entities.UserEntity;
import com.quipux.infraestructure.adapters.out.database.repository.UserRepository;
import com.quipux.infraestructure.adapters.out.mappers.UserMapper;
import com.quipux.infraestructure.port.out.AuthPort;
import com.quipux.infraestructure.security.CustomUserDetailsService;
import com.quipux.infraestructure.security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AuthAdapter implements AuthPort {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserAdapter userAdapter;

    @Override
    public UserComplete login(UserLogin userLogin)  {

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(userLogin.getUsername());

        boolean match = passwordEncoder.matches(userLogin.getPassword(), userDetails.getPassword());
        if (!match) {
            throw new BadCredentialsException("AUTH.BAD.CREDENTIALS");
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userAdapter.getByEmail(userLogin.getUsername());

        return UserComplete.builder()
                .id(user.getId())
                .email(user.getEmail())
                .completeName(user.getCompleteName())
                .token(jwtService.getToken(userDetails))
                .build();
    }

    @Override
    public String register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userAdapter.saveUser(user);

        return "Usuario registrado exitosamente";
    }

}
