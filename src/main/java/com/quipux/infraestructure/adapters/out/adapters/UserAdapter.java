package com.quipux.infraestructure.adapters.out.adapters;

import com.quipux.application.exceptions.BusinessException;
import com.quipux.application.exceptions.ResourceNotFoundException;
import com.quipux.domain.User;
import com.quipux.infraestructure.adapters.out.database.entities.UserEntity;
import com.quipux.infraestructure.adapters.out.database.repository.UserRepository;
import com.quipux.infraestructure.adapters.out.mappers.UserMapper;
import com.quipux.infraestructure.port.out.UserPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class UserAdapter implements UserPort {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public User getByEmail(String email) {

        Optional<UserEntity> userEntity = userRepository.findByEmail(email);

        if(userEntity.isEmpty()) {
            throw new ResourceNotFoundException("USER.NOTFOUND", email);
        }

        return userMapper.entityToDomain(userEntity.get());
    }

    @Override
    public User saveUser(User user) {

        Optional<UserEntity> entity = userRepository.findByEmail(user.getEmail());

        if(entity.isPresent()) {
            throw new BusinessException("USER.REPEAT", user.getEmail());
        }

        UserEntity userEntity = userMapper.domainToEntity(user);

        return userMapper.entityToDomain(userRepository.save(userEntity));
    }

}
