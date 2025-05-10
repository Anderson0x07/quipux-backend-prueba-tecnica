package com.quipux.infrastructure.adapters.in.rest.controller;

import com.quipux.domain.Playlist;
import com.quipux.infraestructure.adapters.in.rest.controller.PlaylistController;
import com.quipux.infraestructure.adapters.in.rest.controller.request.PlaylistRequest;
import com.quipux.infraestructure.adapters.in.rest.controller.response.DescriptionResponse;
import com.quipux.infraestructure.adapters.in.rest.controller.response.PlaylistResponse;
import com.quipux.infraestructure.adapters.in.rest.mappers.PlaylistRestMapper;
import com.quipux.infraestructure.port.in.playlist.PlaylistUsecase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlaylistControllerTest {

    @Mock
    PlaylistUsecase playlistUsecase;

    @Mock
    PlaylistRestMapper playlistRestMapper;

    @InjectMocks
    PlaylistController playlistController;


    List<Playlist> mockPlaylists;
    Playlist mockPlaylist;
    PlaylistResponse mockPlaylistResponse;
    List<PlaylistResponse> mockPlaylistResponses;

    @BeforeEach
    void setUp() {

        mockPlaylist = new Playlist();

        mockPlaylistResponse = new PlaylistResponse();

        mockPlaylists = List.of(mockPlaylist);

        mockPlaylistResponses = List.of(mockPlaylistResponse);
    }


    @Test
    void testGetPlaylist() {
        when(playlistUsecase.getPlaylist()).thenReturn(mockPlaylists);
        when(playlistRestMapper.domainsToResponses(mockPlaylists)).thenReturn(mockPlaylistResponses);

        ResponseEntity<List<PlaylistResponse>> response = playlistController.getPlaylist();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockPlaylistResponses, response.getBody());
        verify(playlistUsecase, times(1)).getPlaylist();
        verify(playlistRestMapper, times(1)).domainsToResponses(mockPlaylists);
    }

    @Test
    void testGetPlaylistDescription() {
        String name = "Rock";

        DescriptionResponse mockDescriptionResponse = new DescriptionResponse();

        when(playlistUsecase.getPlaylistByName(name)).thenReturn(mockPlaylist);
        when(playlistRestMapper.domainToResponseDescription(mockPlaylist)).thenReturn(mockDescriptionResponse);

        ResponseEntity<DescriptionResponse> response = playlistController.getPlaylistDescription(name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDescriptionResponse, response.getBody());
        verify(playlistUsecase, times(1)).getPlaylistByName(name);
        verify(playlistRestMapper, times(1)).domainToResponseDescription(mockPlaylist);
    }

    @Test
    void testCreatePlaylist() {
        PlaylistRequest request = new PlaylistRequest();
        request.setNombre("Jazz");
        Playlist domain = new Playlist();
        domain.setNombre("Jazz");

        when(playlistRestMapper.requestToDomain(request)).thenReturn(domain);
        when(playlistUsecase.save(domain)).thenReturn(domain);
        when(playlistRestMapper.domainToResponse(domain)).thenReturn(mockPlaylistResponse);

        ResponseEntity<PlaylistResponse> response = playlistController.createPlaylist(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockPlaylistResponse, response.getBody());
        verify(playlistRestMapper).requestToDomain(request);
        verify(playlistUsecase).save(domain);
        verify(playlistRestMapper).domainToResponse(domain);
    }

    @Test
    void testDeletePlaylist() {
        String name = "Rock";
        doNothing().when(playlistUsecase).delete(name);

        ResponseEntity<Void> response = playlistController.deletePlaylist(name);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(playlistUsecase).delete(name);
    }

}
