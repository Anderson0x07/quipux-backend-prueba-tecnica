package com.quipux.infraestructure.adapters.out.adapters;

import com.quipux.application.exceptions.ResourceNotFoundException;
import com.quipux.domain.Playlist;
import com.quipux.infraestructure.adapters.out.database.entities.PlaylistEntity;
import com.quipux.infraestructure.adapters.out.database.entities.SongEntity;
import com.quipux.infraestructure.adapters.out.database.repository.PlaylistRepository;
import com.quipux.infraestructure.adapters.out.database.repository.SongRepository;
import com.quipux.infraestructure.adapters.out.mappers.PlaylistMapper;
import com.quipux.infraestructure.port.out.PlaylistPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class PlaylistAdapter implements PlaylistPort {

    PlaylistRepository playlistRepository;
    SongRepository songRepository;
    PlaylistMapper playlistMapper;

    @Override
    public List<Playlist> findAll() {
        return playlistMapper.entitiesToDomains(playlistRepository.findAll());
    }

    @Override
    public Playlist create(Playlist playlist) {
        PlaylistEntity playlistEntity = playlistMapper.domainToEntity(playlist);

        List<SongEntity> canciones = playlist.getCanciones().stream()
                .map(c -> {
                    Optional<SongEntity> cancionExistente = songRepository.findById(c.getId());

                    if(cancionExistente.isEmpty()) {
                        throw new ResourceNotFoundException("SONG.NOTFOUND", c.getTitulo());
                    }

                    cancionExistente.get().setPlaylist(playlistEntity);
                    return cancionExistente.get();
                })
                .collect(Collectors.toList());

        playlistEntity.setCanciones(canciones);

        return playlistMapper.entityToDomain(playlistRepository.save(playlistEntity));
    }

    @Override
    public Playlist findByName(String name) {

        Optional<PlaylistEntity> playlist = playlistRepository.findByNombre(name);

        if(playlist.isEmpty()) {
            throw new ResourceNotFoundException("PLAYLIST.NOTFOUND", name);
        }

        return playlistMapper.entityToDomain(playlist.get());
    }

    @Override
    public void delete(String name) {

        Optional<PlaylistEntity> playlist = playlistRepository.findByNombre(name);

        if(playlist.isEmpty()) {
            throw new ResourceNotFoundException("PLAYLIST.NOTFOUND", name);
        }


        playlistRepository.delete(playlist.get());

    }
}
