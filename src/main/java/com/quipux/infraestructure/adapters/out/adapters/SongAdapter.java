package com.quipux.infraestructure.adapters.out.adapters;

import com.quipux.application.exceptions.ResourceNotFoundException;
import com.quipux.domain.Song;
import com.quipux.infraestructure.adapters.out.database.entities.PlaylistEntity;
import com.quipux.infraestructure.adapters.out.database.entities.SongEntity;
import com.quipux.infraestructure.adapters.out.database.repository.PlaylistRepository;
import com.quipux.infraestructure.adapters.out.database.repository.SongRepository;
import com.quipux.infraestructure.adapters.out.mappers.SongMapper;
import com.quipux.infraestructure.port.out.SongPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class SongAdapter implements SongPort {

    SongRepository songRepository;
    PlaylistRepository playlistRepository;
    SongMapper songMapper;

    @Override
    public List<Song> findAll() {

        List<SongEntity> list = songRepository.findAll();
        return songMapper.entitiesToDomains(list);
    }

    @Override
    public Song create(Song song) {
        SongEntity songEntity = songMapper.domainToEntity(song);
        return songMapper.entityToDomain(songRepository.save(songEntity));
    }

    @Override
    public String addToPlaylist(String playlistName, Long songId) {

        Optional<SongEntity> song = songRepository.findById(songId);

        if(song.isEmpty()) {
            throw new ResourceNotFoundException("SONG.NOTFOUND", songId);
        }

        Optional<PlaylistEntity> playlist = playlistRepository.findByNombre(playlistName);

        if(playlist.isEmpty()) {
            throw new ResourceNotFoundException("PLAYLIST.NOTFOUND", playlistName);
        }

        if (!playlist.get().getCanciones().contains(song)) {
            playlist.get().getCanciones().add(song.get());
            playlistRepository.save(playlist.get());
        }

        return "Canción agregada a la playlist exitosamente";
    }


}
