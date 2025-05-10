package com.quipux.application.usecases;

import com.quipux.application.usecases.song.SongUsecaseImpl;
import com.quipux.domain.Song;
import com.quipux.infraestructure.port.out.SongPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SongUsecaseImplTest {

    private SongPort songPort;
    private SongUsecaseImpl songUsecase;

    @BeforeEach
    void setUp() {
        songPort = mock(SongPort.class);
        songUsecase = new SongUsecaseImpl(songPort);
    }

    @Test
    void testGetSong() {
        Song song = new Song();
        List<Song> mockSongs = List.of(song);
        when(songPort.findAll()).thenReturn(mockSongs);

        List<Song> result = songUsecase.getSong();

        assertEquals(1, result.size());
        verify(songPort).findAll();
    }

    @Test
    void testAddToPlaylist() {
        String playlistName = "Chill";
        Long songId = 1L;
        String expectedResult = "Song added";

        when(songPort.addToPlaylist(playlistName, songId)).thenReturn(expectedResult);

        String result = songUsecase.addToPlaylist(playlistName, songId);

        assertEquals(expectedResult, result);
        verify(songPort).addToPlaylist(playlistName, songId);
    }

    @Test
    void testSave() {
        Song song = new Song();
        song.setTitulo("Rock");
        when(songPort.create(song)).thenReturn(song);

        Song result = songUsecase.save(song);

        assertEquals("Rock", result.getTitulo());
        verify(songPort).create(song);
    }
}
