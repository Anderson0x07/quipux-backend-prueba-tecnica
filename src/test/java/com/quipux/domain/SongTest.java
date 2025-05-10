package com.quipux.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class SongTest {

    @Test
    void testSettersAndGetters() {
        Long id = 1L;
        String titulo = "TITULO";
        String genero = "GENERO";
        String album = "ALBUM";
        String anno = "2025";
        String artista = "artista";

        Song songTest = new Song();
        songTest.setId(id);
        songTest.setTitulo(titulo);
        songTest.setGenero(genero);
        songTest.setAlbum(album);
        songTest.setAnno(anno);
        songTest.setArtista(artista);

        assertEquals(id, songTest.getId());
        assertEquals(titulo, songTest.getTitulo());
        assertEquals(genero, songTest.getGenero());
        assertEquals(album, songTest.getAlbum());
        assertEquals(anno, songTest.getAnno());
        assertEquals(artista, songTest.getArtista());
    }
}
