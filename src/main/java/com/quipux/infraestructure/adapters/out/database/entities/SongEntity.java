package com.quipux.infraestructure.adapters.out.database.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "song")
@Getter
@Setter
@RequiredArgsConstructor
public class SongEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "canciones")
    private List<PlaylistEntity> playlists = new ArrayList<>();

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "artista", nullable = false)
    private String artista;

    @Column(name = "album", nullable = false)
    private String album;

    @Column(name = "anno", nullable = false)
    private String anno;

    @Column(name = "genero", nullable = false)
    private String genero;
}
