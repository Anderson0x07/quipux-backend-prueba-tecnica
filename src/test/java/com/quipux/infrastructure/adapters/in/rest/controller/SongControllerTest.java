package com.quipux.infrastructure.adapters.in.rest.controller;

import com.quipux.domain.Song;
import com.quipux.infraestructure.adapters.in.rest.controller.SongController;
import com.quipux.infraestructure.adapters.in.rest.controller.request.SongRequest;
import com.quipux.infraestructure.adapters.in.rest.controller.response.DescriptionResponse;
import com.quipux.infraestructure.adapters.in.rest.controller.response.SongResponse;
import com.quipux.infraestructure.adapters.in.rest.mappers.SongRestMapper;
import com.quipux.infraestructure.port.in.song.SongUsecase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SongControllerTest {

    private SongUsecase songUsecase;
    private SongRestMapper songRestMapper;
    private SongController songController;

    private List<Song> mockSongs;
    private List<SongResponse> mockResponses;
    private Song mockSong;
    private SongResponse mockSongResponse;
    private SongRequest mockSongRequest;
    private DescriptionResponse mockDescriptionResponse;

    @BeforeEach
    void setUp() {
        songUsecase = mock(SongUsecase.class);
        songRestMapper = mock(SongRestMapper.class);
        songController = new SongController(songUsecase, songRestMapper);

        mockSong = new Song();
        mockSong.setTitulo("Test Song");
        mockSongs = List.of(mockSong);
        mockSongResponse = new SongResponse();
        mockSongResponse.setTitulo("Test Song");
        mockResponses = List.of(mockSongResponse);
        mockSongRequest = new SongRequest();
        mockDescriptionResponse = new DescriptionResponse();
        mockDescriptionResponse.setDescripcion("Playlist descripcion");
    }

    @Test
    void testGetSongs() {
        when(songUsecase.getSong()).thenReturn(mockSongs);
        when(songRestMapper.domainsToResponses(mockSongs)).thenReturn(mockResponses);

        ResponseEntity<List<SongResponse>> response = songController.getSongs();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponses, response.getBody());

        verify(songUsecase).getSong();
        verify(songRestMapper).domainsToResponses(mockSongs);
    }

    @Test
    void testAddToPlaylist() {
        String playlistName = "Favoritos";
        Long songId = 1L;
        String description = "Playlist descripcion";

        when(songUsecase.addToPlaylist(playlistName, songId)).thenReturn(description);

        ResponseEntity<DescriptionResponse> response = songController.addToPlaylist(playlistName, songId);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(songUsecase).addToPlaylist(playlistName, songId);
    }

    @Test
    void testCreateSong() {
        when(songRestMapper.requestToDomain(mockSongRequest)).thenReturn(mockSong);
        when(songUsecase.save(mockSong)).thenReturn(mockSong);
        when(songRestMapper.domainToResponse(mockSong)).thenReturn(mockSongResponse);

        ResponseEntity<SongResponse> response = songController.createSong(mockSongRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockSongResponse, response.getBody());

        verify(songRestMapper).requestToDomain(mockSongRequest);
        verify(songUsecase).save(mockSong);
        verify(songRestMapper).domainToResponse(mockSong);
    }

}
