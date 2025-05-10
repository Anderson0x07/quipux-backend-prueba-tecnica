package com.quipux.application.usecases.song;

import com.quipux.domain.Song;
import com.quipux.infraestructure.port.in.song.SongUsecase;
import com.quipux.infraestructure.port.out.SongPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SongUsecaseImpl implements SongUsecase {

    private final SongPort songPort;

    @Override
    public List<Song> getSong() {
        return songPort.findAll();
    }

    @Override
    public String addToPlaylist(String playlistName, Long songId) {
        return songPort.addToPlaylist(playlistName, songId);
    }

    @Override
    public Song save(Song song) {
        return songPort.create(song);
    }
}
