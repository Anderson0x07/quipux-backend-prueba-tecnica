package com.quipux.infraestructure.adapters.in.rest.controller.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class SongResponse {
    private Long id;
    private String titulo;
    private String artista;
    private String album;
    private String anno;
    private String genero;
}
