package com.quipux.application.usecases.playlist;

import com.quipux.domain.Playlist;
import com.quipux.infraestructure.port.in.playlist.PlaylistUsecase;
import com.quipux.infraestructure.port.out.PlaylistPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PlaylistUsecaseImpl implements PlaylistUsecase {

    private final PlaylistPort playlistPort;

    @Override
    public List<Playlist> getPlaylist() {
        return playlistPort.findAll();
    }

    @Override
    public void delete(String name) {
        playlistPort.delete(name);
    }

    @Override
    public Playlist getPlaylistByName(String name) {
        return playlistPort.findByName(name);
    }

    @Override
    public Playlist save(Playlist user) {
        return playlistPort.create(user);
    }

}
