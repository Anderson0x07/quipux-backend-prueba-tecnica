package com.quipux.infraestructure.adapters.in.rest.controller;

import com.quipux.domain.Playlist;
import com.quipux.domain.Song;
import com.quipux.infraestructure.adapters.in.rest.configuration.SongApi;
import com.quipux.infraestructure.adapters.in.rest.controller.request.SongRequest;
import com.quipux.infraestructure.adapters.in.rest.controller.response.DescriptionResponse;
import com.quipux.infraestructure.adapters.in.rest.controller.response.SongResponse;
import com.quipux.infraestructure.adapters.in.rest.mappers.SongRestMapper;
import com.quipux.infraestructure.port.in.song.SongUsecase;
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
@RequestMapping("/songs")
@Validated
public class SongController implements SongApi {

    private final SongUsecase songUsecase;
    private final SongRestMapper songRestMapper;


    @Override
    @GetMapping
    public ResponseEntity<List<SongResponse>> getSongs() {
        return new ResponseEntity<>(songRestMapper.domainsToResponses(songUsecase.getSong()), HttpStatus.OK);
    }

    @Override
    @PutMapping
    public ResponseEntity<DescriptionResponse> addToPlaylist(@RequestParam @NotNull String playlistName, @RequestParam @NotNull Long songId) {

        String description = songUsecase.addToPlaylist(playlistName, songId);

        DescriptionResponse descriptionResponse = new DescriptionResponse();
        descriptionResponse.setDescripcion(description);

        return new ResponseEntity<>(descriptionResponse, HttpStatus.OK);

    }

    @Override
    @PostMapping
    public ResponseEntity<SongResponse> createSong(@Valid SongRequest songRequest) {
        Song domain = songRestMapper.requestToDomain(songRequest);
        return new ResponseEntity<>(songRestMapper.domainToResponse(songUsecase.save(domain)), HttpStatus.CREATED);
    }
}
