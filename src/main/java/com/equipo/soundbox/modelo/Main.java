package com.equipo.soundbox.modelo;

import com.equipo.soundbox.colecciones.GestorAlbumes;
import com.equipo.soundbox.consola.MenuConsola;
import com.equipo.soundbox.persistencia.GestorFicheros;

/**
 * Punto de entrada de la aplicación SoundBox.
 *
 * @author José y Ruben
 * @version 2.0
 */
public class Main {

    /**
     * Método principal que lanza el menú de consola.
     *
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        GestorAlbumes gestor = new GestorAlbumes();
        GestorFicheros ficheros = new GestorFicheros("datos/catalogo.csv");
        MenuConsola menu = new MenuConsola(gestor, ficheros);
        menu.ejecutar();
    }
}