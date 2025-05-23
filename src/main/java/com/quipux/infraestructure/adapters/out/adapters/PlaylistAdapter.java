package com.quipux.infraestructure.adapters.out.adapters;

import com.quipux.application.exceptions.BusinessException;
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

        Optional<PlaylistEntity> playlistRepetida = playlistRepository.findByNombre(playlist.getNombre());

        if(playlistRepetida.isPresent()) {
            throw new BusinessException("PLAYLIST.REPEAT", playlist.getNombre());
        }

        PlaylistEntity playlistEntity = playlistMapper.domainToEntity(playlist);

        List<SongEntity> canciones = playlist.getCanciones().stream()
                .map(c -> {

                    if(c.getId() == null) {
                        throw new ResourceNotFoundException("SONG.NOTFOUND", c.getTitulo());
                    }

                    Optional<SongEntity> cancionExistente = songRepository.findById(c.getId());

                    if(cancionExistente.isEmpty()) {
                        throw new ResourceNotFoundException("SONG.NOTFOUND", c.getTitulo());
                    }
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

        playlist.get().getCanciones().forEach(song -> song.getPlaylists().remove(playlist.get()));
        playlist.get().getCanciones().clear();


        playlistRepository.delete(playlist.get());

    }
}
