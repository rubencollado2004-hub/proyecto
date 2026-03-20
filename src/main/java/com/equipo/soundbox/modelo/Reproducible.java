package com.equipo.soundbox.modelo;

/**
 * Interfaz que define el comportamiento de reproducción de un álbum.
 *
 * @author José y Ruben
 * @version 1.0
 */
public interface Reproducible {

    /**
     * Reproduce el álbum mostrando información por consola.
     */
    void reproducir();

    /**
     * Devuelve la duración total del álbum en segundos.
     *
     * @return duración en segundos
     */
    int getDuracionTotal();
}