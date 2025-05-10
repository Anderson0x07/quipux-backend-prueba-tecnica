package com.quipux.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PlaylistTest {

    @Test
    void testSettersAndGetters() {
        Long id = 1L;
        String nombre = "NOMBRE";
        String descripcion = "DESCRIPCION";

        Playlist playlistTest = new Playlist();
        playlistTest.setId(id);
        playlistTest.setNombre(nombre);
        playlistTest.setDescripcion(descripcion);

        assertEquals(id, playlistTest.getId());
        assertEquals(nombre, playlistTest.getNombre());
        assertEquals(descripcion, playlistTest.getDescripcion());
    }
}
