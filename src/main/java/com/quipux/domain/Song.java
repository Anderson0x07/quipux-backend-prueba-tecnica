package com.quipux.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Song {
    private Long id;
    private String titulo;
    private String artista;
    private String album;
    private String anno;
    private String genero;
}
