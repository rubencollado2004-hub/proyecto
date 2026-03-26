package com.equipo.soundbox.colecciones;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.equipo.soundbox.modelo.Album;
import com.equipo.soundbox.modelo.AlbumDigital;
import com.equipo.soundbox.modelo.AlbumFisico;

/**
 * Gestor de colecciones de álbumes con métodos para añadir, eliminar, buscar y filtrar.
 *
 * @author José y Ruben
 * @version 1.0
 */
public class GestorAlbumes {

    private final ArrayList<Album> catalogo;

    /**
     * Constructor sin parámetros que inicializa el catálogo.
     */
    public GestorAlbumes() {
        this.catalogo = new ArrayList<>();
    }

    /**
     * Obtiene el catálogo de álbumes.
     *
     * @return lista de álbumes
     */
    public ArrayList<Album> getCatalogo() {
        return catalogo;
    }

    /**
     * Añade un álbum al catálogo.
     *
     * @param album el álbum a añadir
     */
    public void añadir(Album album) {
        if (album != null) {
            catalogo.add(album);
        }
    }

    /**
     * Elimina un álbum del catálogo por título.
     *
     * @param titulo el título del álbum a eliminar
     * @return true si se eliminó, false si no se encontró
     */
    public boolean eliminar(String titulo) {
        return catalogo.removeIf(a -> a.getTitulo().equalsIgnoreCase(titulo));
    }

    /**
     * Busca álbumes por artista.
     *
     * @param artista el nombre del artista
     * @return lista de álbumes del artista
     */
    public List<Album> buscarPorArtista(String artista) {
        return catalogo.stream()
                .filter(a -> a.getArtista().toLowerCase().contains(artista.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Filtra álbumes por tipo (Físico o Digital).
     *
     * @param tipo el tipo a filtrar ("Físico" o "Digital")
     * @return lista de álbumes del tipo especificado
     */
    public List<Album> filtrarPorTipo(String tipo) {
        return catalogo.stream()
                .filter(a -> a.getTipo().toLowerCase().contains(tipo.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Exporta el catálogo a formato JSON.
     *
     * @return string con el formato JSON
     */
    public String exportarJSON() {
        StringBuilder json = new StringBuilder("[\n");
        for (int i = 0; i < catalogo.size(); i++) {
            Album a = catalogo.get(i);
            json.append("  {\n");
            json.append("    \"titulo\": \"").append(a.getTitulo()).append("\",\n");
            json.append("    \"artista\": \"").append(a.getArtista()).append("\",\n");
            json.append("    \"año\": ").append(a.getAño()).append(",\n");
            json.append("    \"puntuacion\": ").append(a.getPuntuacion()).append(",\n");
            json.append("    \"tipo\": \"").append(a.getTipo()).append("\"");
            if (a instanceof AlbumFisico af) {
                json.append(",\n    \"formato\": \"").append(af.getFormato()).append("\",\n");
                json.append("    \"numDiscos\": ").append(af.getNumDiscos());
            } else if (a instanceof AlbumDigital ad) {
                json.append(",\n    \"plataforma\": \"").append(ad.getPlataforma()).append("\",\n");
                json.append("    \"bitrate\": ").append(ad.getBitrate());
            }
            json.append("\n  }");
            if (i < catalogo.size() - 1) json.append(",");
            json.append("\n");
        }
        json.append("]");
        return json.toString();
    }

    /**
     * Devuelve el álbum con mayor puntuación de una lista.
     *
     * @param <T>   tipo que extiende Album
     * @param lista lista de álbumes a evaluar
     * @return el álbum mejor valorado
     * @throws IllegalArgumentException si la lista está vacía
     */
    public static <T extends Album> T getMejorValorado(ArrayList<T> lista) {
        if (lista == null || lista.isEmpty())
            throw new IllegalArgumentException("La lista no puede estar vacía");
        T mejor = lista.get(0);
        for (T a : lista) {
            if (a.getPuntuacion() > mejor.getPuntuacion()) mejor = a;
        }
        return mejor;
    }
}