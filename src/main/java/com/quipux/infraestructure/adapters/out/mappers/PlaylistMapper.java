package com.quipux.infraestructure.adapters.out.mappers;

import com.quipux.domain.Playlist;
import com.quipux.infraestructure.adapters.out.database.entities.PlaylistEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {
    Playlist entityToDomain(PlaylistEntity entity);
    List<Playlist> entitiesToDomains(List<PlaylistEntity> entity);
    PlaylistEntity domainToEntity(Playlist domain);
}
