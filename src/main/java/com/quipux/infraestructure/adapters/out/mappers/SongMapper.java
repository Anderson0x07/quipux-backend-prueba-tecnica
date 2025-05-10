package com.quipux.infraestructure.adapters.out.mappers;

import com.quipux.domain.Song;
import com.quipux.infraestructure.adapters.out.database.entities.SongEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SongMapper {
    Song entityToDomain(SongEntity entity);
    List<Song> entitiesToDomains(List<SongEntity> entity);
    SongEntity domainToEntity(Song domain);
}
