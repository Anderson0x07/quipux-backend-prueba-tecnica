package com.quipux.infraestructure.adapters.in.rest.configuration;

import com.quipux.infraestructure.adapters.in.rest.controller.request.SongRequest;
import com.quipux.infraestructure.adapters.in.rest.controller.response.DescriptionResponse;
import com.quipux.infraestructure.adapters.in.rest.controller.response.SongResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Song", description = "Api para gestion de canciones")
public interface SongApi {

    @Operation(summary = "Ver todas las canciones", description = "Responde con un listado de canciones")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Solicitud exitosa"),})
    ResponseEntity<List<SongResponse>> getSongs();

    @Operation(summary = "Agregar canción a playlist", description = "Responde con el mensaje exitoso")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Solicitud exitosa"),})
    ResponseEntity<DescriptionResponse> addToPlaylist(String playlistName, Long songId);

    @Operation(summary = "Crear Canción", description = "Responde con la canción creada")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Canción creada"),})
    ResponseEntity<SongResponse> createSong(SongRequest playlistRequest);

}
