package com.quipux.infraestructure.adapters.out.adapters;

import com.quipux.application.exceptions.ResourceNotFoundException;
import com.quipux.domain.Playlist;
import com.quipux.infraestructure.adapters.out.database.entities.PlaylistEntity;
import com.quipux.infraestructure.adapters.out.database.repository.PlaylistRepository;
import com.quipux.infraestructure.adapters.out.mappers.PlaylistMapper;
import com.quipux.infraestructure.port.out.PlaylistPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class PlaylistAdapter implements PlaylistPort {

    PlaylistRepository playlistRepository;
    PlaylistMapper playlistMapper;

    @Override
    public List<Playlist> findAll() {
        return playlistMapper.entitiesToDomains(playlistRepository.findAll());
    }

    @Override
    public Playlist create(Playlist user) {
        return playlistMapper.entityToDomain(playlistRepository.save(playlistMapper.domainToEntity(user)));
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
