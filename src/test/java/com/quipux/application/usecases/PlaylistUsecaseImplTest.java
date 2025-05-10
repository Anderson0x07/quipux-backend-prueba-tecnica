package com.quipux.application.usecases;

import com.quipux.application.usecases.playlist.PlaylistUsecaseImpl;
import com.quipux.domain.Playlist;
import com.quipux.infraestructure.port.out.PlaylistPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlaylistUsecaseImplTest {

    @Mock
    private PlaylistPort playlistPort;

    @InjectMocks
    private PlaylistUsecaseImpl playlistUsecase;

    private Playlist playlist;

    @BeforeEach
    void setUp() {
        playlist = new Playlist();

        playlist.setId(1L);
        playlist.setNombre("Rock");
        playlist.setDescripcion("Description");
        playlist.setCanciones(List.of());
    }

    @Test
    void testGetPlaylist() {
        List<Playlist> mockPlaylists = List.of(playlist);
        when(playlistPort.findAll()).thenReturn(mockPlaylists);

        List<Playlist> result = playlistUsecase.getPlaylist();

        assertEquals(1, result.size());
        verify(playlistPort).findAll();
    }

    @Test
    void testDelete() {
        String name = "Rock";
        doNothing().when(playlistPort).delete(name);

        playlistUsecase.delete(name);

        verify(playlistPort).delete(name);
    }

    @Test
    void testGetPlaylistByName() {
        String name = "Rock";
        when(playlistPort.findByName(name)).thenReturn(playlist);

        Playlist result = playlistUsecase.getPlaylistByName(name);

        assertNotNull(result);
        assertEquals(name, result.getNombre());
        verify(playlistPort).findByName(name);
    }

    @Test
    void testSave() {
        when(playlistPort.create(playlist)).thenReturn(playlist);

        Playlist result = playlistUsecase.save(playlist);

        assertEquals("Rock", result.getNombre());
        verify(playlistPort).create(playlist);
    }
}
