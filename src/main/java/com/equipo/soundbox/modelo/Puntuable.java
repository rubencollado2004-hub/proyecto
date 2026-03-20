package com.equipo.soundbox.modelo;

/**
 * Interfaz que define el comportamiento de puntuación de un álbum.
 *
 * @author José y Ruben
 * @version 1.0
 */
public interface Puntuable {

    /**
     * Establece la puntuación del álbum.
     *
     * @param puntuacion valor entre 0.0 y 10.0
     */
    void setPuntuacion(double puntuacion);

    /**
     * Devuelve la puntuación actual del álbum.
     *
     * @return puntuación entre 0.0 y 10.0
     */
    double getPuntuacion();
}