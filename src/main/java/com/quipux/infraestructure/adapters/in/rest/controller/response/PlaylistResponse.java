package com.quipux.infraestructure.adapters.in.rest.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlaylistResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private List<SongResponse> canciones;
}
