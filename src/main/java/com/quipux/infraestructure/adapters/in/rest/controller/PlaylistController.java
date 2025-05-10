package com.quipux.infraestructure.adapters.in.rest.controller;

import com.quipux.domain.Playlist;
import com.quipux.infraestructure.adapters.in.rest.configuration.PlaylistApi;
import com.quipux.infraestructure.adapters.in.rest.controller.response.DescriptionResponse;
import com.quipux.infraestructure.adapters.in.rest.controller.response.PlaylistResponse;
import com.quipux.infraestructure.adapters.in.rest.mappers.PlaylistRestMapper;
import com.quipux.infraestructure.port.in.playlist.PlaylistUsecase;
import com.quipux.infraestructure.adapters.in.rest.controller.request.PlaylistRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lists")
@Validated
public class PlaylistController implements PlaylistApi {

    private final PlaylistUsecase playlistUsecase;
    private final PlaylistRestMapper playlistRestMapper;

    @Override
    @GetMapping
    public ResponseEntity<List<PlaylistResponse>> getPlaylist() {
        return new ResponseEntity<>(playlistRestMapper.domainsToResponses(playlistUsecase.getPlaylist()), HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<PlaylistResponse> createPlaylist(@RequestBody @Valid PlaylistRequest playlistRequest) {
        Playlist domain = playlistRestMapper.requestToDomain(playlistRequest);
        return new ResponseEntity<>(playlistRestMapper.domainToResponse(playlistUsecase.save(domain)), HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{listName}")
    public ResponseEntity<DescriptionResponse> getPlaylistDescription(@PathVariable @NotNull String listName) {
        Playlist domain = playlistUsecase.getPlaylistByName(listName);
        return new ResponseEntity<>(playlistRestMapper.domainToResponseDescription(domain), HttpStatus.OK);

    }


    @Override
    @DeleteMapping("/{listName}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable @NotNull String listName) {
        playlistUsecase.delete(listName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
