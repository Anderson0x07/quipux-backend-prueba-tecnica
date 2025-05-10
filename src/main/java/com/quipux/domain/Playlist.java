package com.quipux.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class Playlist implements Serializable {
    private Long id;
    private String nombre;
    private String descripcion;
    private List<Song> canciones;
}
