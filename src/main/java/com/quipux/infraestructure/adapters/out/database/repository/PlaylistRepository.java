package com.quipux.infraestructure.adapters.out.database.repository;

import com.quipux.infraestructure.adapters.out.database.entities.PlaylistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaylistRepository extends JpaRepository<PlaylistEntity, Long> {

    Optional<PlaylistEntity> findByNombre(String name);
}
