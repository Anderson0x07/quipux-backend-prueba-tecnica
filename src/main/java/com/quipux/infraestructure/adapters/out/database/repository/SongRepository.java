package com.quipux.infraestructure.adapters.out.database.repository;

import com.quipux.infraestructure.adapters.out.database.entities.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<SongEntity, Long> {

}
