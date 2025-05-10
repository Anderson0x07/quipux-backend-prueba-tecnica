package com.quipux.infraestructure.adapters.out.database.repository;

import com.quipux.infraestructure.adapters.out.database.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
}
