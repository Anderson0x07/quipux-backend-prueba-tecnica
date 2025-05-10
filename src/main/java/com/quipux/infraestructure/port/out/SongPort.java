package com.quipux.infraestructure.port.out;

import com.quipux.domain.Song;

import java.util.List;

public interface SongPort {

    List<Song> findAll();

    Song create(Song playlist);

    String addToPlaylist(String playlistName, Long songId);

}
