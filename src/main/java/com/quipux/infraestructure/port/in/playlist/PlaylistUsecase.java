package com.quipux.infraestructure.port.in.playlist;

import com.quipux.domain.Playlist;

import java.util.List;

public interface PlaylistUsecase {
    List<Playlist> getPlaylist();

    void delete(String name);

    Playlist getPlaylistByName(String name);

    Playlist save(Playlist user);



}
