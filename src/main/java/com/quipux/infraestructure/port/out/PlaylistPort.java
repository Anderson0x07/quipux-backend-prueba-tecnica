package com.quipux.infraestructure.port.out;

import com.quipux.domain.Playlist;

import java.util.List;

public interface PlaylistPort {

    List<Playlist> findAll();

    Playlist create(Playlist playlist);

    Playlist findByName(String name);

    void delete(String name);
}
