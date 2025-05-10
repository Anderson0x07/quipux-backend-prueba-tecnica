package com.quipux.infraestructure.adapters.in.rest.configuration;

import com.quipux.infraestructure.adapters.in.rest.controller.request.PlaylistRequest;
import com.quipux.infraestructure.adapters.in.rest.controller.response.DescriptionResponse;
import com.quipux.infraestructure.adapters.in.rest.controller.response.PlaylistResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Playlist", description = "Api para gestion de listas de reproducción")
public interface PlaylistApi {

    @Operation(summary = "Ver todas las listas de reproducción existentes", description = "Responde con un listado de playlist")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Solicitud exitosa"),})
    ResponseEntity<List<PlaylistResponse>> getPlaylist();

    @Operation(summary = "Obtener Playlist", description = "Responde con un listado de playlist")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Solicitud exitosa"),})
    ResponseEntity<DescriptionResponse> getPlaylistDescription(String listName);

    @Operation(summary = "Crear Playlist", description = "Responde con la playlist creada")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Playlist creada"),})
    ResponseEntity<PlaylistResponse> createPlaylist(PlaylistRequest playlistRequest);

    @Operation(summary = "Eliminar Playlist", description = "Borrar una lista de reproducción.")
    @ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Playlist eliminada"),})
    ResponseEntity<?> deletePlaylist(String listName);
}
