package com.quipux.infraestructure.adapters.out.mappers;

import com.quipux.domain.User;
import com.quipux.infraestructure.adapters.out.database.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User entityToDomain(UserEntity entity);
    UserEntity domainToEntity(User domain);
}
