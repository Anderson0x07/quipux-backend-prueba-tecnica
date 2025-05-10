package com.quipux.infraestructure.adapters.in.rest.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SongRequest {
    @NotNull
    private String titulo;
    @NotNull
    private String artista;
    @NotNull
    private String album;
    @NotNull
    private String anno;
    @NotNull
    private String genero;
}
