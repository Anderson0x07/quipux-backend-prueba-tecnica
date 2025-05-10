package com.quipux.infraestructure.port.in.song;

import com.quipux.domain.Song;

import java.util.List;

public interface SongUsecase {

    List<Song> getSong();

    String addToPlaylist(String playlistName, Long songId);

    Song save(Song user);

}
