package com.equipo.soundbox.persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import com.equipo.soundbox.modelo.Album;
import com.equipo.soundbox.modelo.AlbumDigital;
import com.equipo.soundbox.modelo.AlbumFisico;

/**
 * Gestiona la persistencia de álbumes en ficheros CSV.
 * Usa BufferedReader para lectura, FileWriter para escritura
 * y RandomAccessFile para acceso por posición.
 *
 * @author José y Ruben
 * @version 1.0
 */
public class GestorFicheros {

    private final String rutaCSV;

    /**
     * Constructor de GestorFicheros.
     *
     * @param rutaCSV ruta al fichero CSV de datos
     */
    public GestorFicheros(String rutaCSV) {
        this.rutaCSV = rutaCSV;
    }

    /**
     * Carga los álbumes desde el fichero CSV.
     * Formato: tipo,titulo,artista,anio,puntuacion,dato1,dato2
     *
     * @return lista de álbumes cargados
     */
    public List<Album> cargar() {
        List<Album> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaCSV))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) continue;
                String[] p = linea.split(",");
                if (p[0].equals("Fisico")) {
                    AlbumFisico a = new AlbumFisico(
                            p[1], p[2], Integer.parseInt(p[3]), p[5], Integer.parseInt(p[6]));
                    a.setPuntuacion(Double.parseDouble(p[4]));
                    lista.add(a);
                } else if (p[0].equals("Digital")) {
                    AlbumDigital a = new AlbumDigital(
                            p[1], p[2], Integer.parseInt(p[3]), p[5], Integer.parseInt(p[6]));
                    a.setPuntuacion(Double.parseDouble(p[4]));
                    lista.add(a);
                }
            }
        } catch (IOException e) {
            System.out.println("Fichero no encontrado, se iniciará vacío.");
        }
        return lista;
    }

    /**
     * Guarda la lista de álbumes en el fichero CSV.
     *
     * @param lista lista de álbumes a guardar
     */
    public void guardar(List<Album> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaCSV))) {
            for (Album a : lista) {
                if (a instanceof AlbumFisico af) {
                    bw.write("Fisico," + af.getTitulo() + "," + af.getArtista()
                            + "," + af.getAño() + "," + af.getPuntuacion()
                            + "," + af.getFormato() + "," + af.getNumDiscos());
                } else if (a instanceof AlbumDigital ad) {
                    bw.write("Digital," + ad.getTitulo() + "," + ad.getArtista()
                            + "," + ad.getAño() + "," + ad.getPuntuacion()
                            + "," + ad.getPlataforma() + "," + ad.getBitrate());
                }
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    /**
     * Busca una línea del CSV por número de línea usando RandomAccessFile.
     *
     * @param numeroLinea número de línea a buscar (desde 0)
     * @return contenido de la línea o null si no existe
     */
    public String buscarPorLinea(int numeroLinea) {
        try (RandomAccessFile raf = new RandomAccessFile(rutaCSV, "r")) {
            int lineaActual = 0;
            String linea;
            while ((linea = raf.readLine()) != null) {
                if (lineaActual == numeroLinea) return linea;
                lineaActual++;
            }
        } catch (IOException e) {
            System.out.println("Error al leer: " + e.getMessage());
        }
        return null;
    }

    /**
     * Exporta el JSON a un fichero.
     *
     * @param json    contenido JSON a exportar
     * @param rutaJSON ruta del fichero de salida
     */
    public void exportarJSON(String json, String rutaJSON) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaJSON))) {
            bw.write(json);
        } catch (IOException e) {
            System.out.println("Error al exportar JSON: " + e.getMessage());
        }
    }

    /**
     * Devuelve la ruta del fichero CSV.
     *
     * @return ruta CSV
     */
    public String getRutaCSV() {
        return rutaCSV;
    }
}