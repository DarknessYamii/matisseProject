/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matissetrack;

import TrackList.track;
import java.util.Scanner;
import com.matisse.MtDatabase;
import com.matisse.MtException;
import com.matisse.MtObjectIterator;
import com.matisse.MtPackageObjectFactory;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author andre
 */
public class MatisseTrack {

    public static final String ANSI_PURPLE = "\u001b[35m";
    public static final String ANSI_RED = "\u001b[31m";
    public static final String ANSI_RESET = "\u001b[0m";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int Opcion = 0;
        do {
            String hostname = "localhost";
            String dbname = "DAM";

            Scanner sc = new Scanner(System.in);
            System.out.println(ANSI_PURPLE + "------------------------" + ANSI_RESET);
            System.out.println(ANSI_RED + "--TRACKLIST--" + ANSI_RESET);
            // Menu seleccion
            System.out.println("¿Que desea hacer?");
            System.out.println("1.-Agregar Cancion (Todos los campos rellenos)" + "\n2.-Listar todo"
                    + "\n3.-Listar Canciones segun Artista" + "\n4.-Listar Canciones (Cancion, Album, Artista) "
                    + "\n5.-Borrar Canciones" + "\n6.-Modificar AlbumName" + "\nCualquier otro numero.-Cerrar APP");
            Opcion = sc.nextInt();
            switch (Opcion) {
                case 1:
                    addTrack(hostname, dbname);
                    break;
                case 2:
                    searchAll(hostname, dbname);
                    break;
                case 3:
                    searchSongByArtist(hostname, dbname);
                    break;
                case 4:
                    searchEverySong(hostname, dbname);
                    break;
                case 5:
                    deleteSong(hostname, dbname);
                    break;
                case 6:
                    modifySong(hostname, dbname);
                    break;
            }

        } while (Opcion < 7 && Opcion > 0);
    }

    // Aniade un nuevo Track a nuestra BBDD
    public static void addTrack(String hostname, String dbname) {
        try {
            Scanner sc = new Scanner(System.in);
//Abre la base de datos con el Hostname (localhost), dbname (DAM) y es namespace "TrackList".
            MtDatabase db = new MtDatabase(hostname, dbname, new MtPackageObjectFactory("", "DAM"));
            db.open();
            db.startTransaction();
// Crea un objeto Track
            track tr = new track(db);

            System.out.println("Track URI:");
            String TrackURI = sc.nextLine();
            while (TrackURI.isEmpty()) {
                System.out.println("TrackURI");
                TrackURI = sc.nextLine();
            }
            tr.setTrackURI(TrackURI);

            System.out.println("Track Name:");
            String TrackName = sc.nextLine();
            while (TrackName.isEmpty()) {
                System.out.println("Track Name:");
                TrackName = sc.nextLine();
            }
            tr.setTrackName(TrackName);

            System.out.println("Artist URIs:");
            String ArtistURIs = sc.nextLine();
            while (ArtistURIs.isEmpty()) {
                System.out.println("Artist URIs:");
                ArtistURIs = sc.nextLine();
            }
            tr.setArtistURIs(ArtistURIs);

            System.out.println("Artist Names:");
            String ArtistNames = sc.nextLine();
            while (ArtistNames.isEmpty()) {
                System.out.println("Artist Names:");
                ArtistNames = sc.nextLine();
            }
            tr.setArtistNames(ArtistNames);

            System.out.println("Album URI:");
            String AlbumURI = sc.nextLine();
            while (AlbumURI.isEmpty()) {
                System.out.println("Album URI:");
                AlbumURI = sc.nextLine();
            }
            tr.setAlbumURI(AlbumURI);

            System.out.println("Album Name:");
            String AlbumName = sc.nextLine();
            while (AlbumName.isEmpty()) {
                System.out.println("Album Name:");
                AlbumName = sc.nextLine();
            }
            tr.setAlbumName(AlbumName);

            System.out.println("Album Artist URIs:");
            String AlbumArtistURIs = sc.nextLine();
            while (AlbumArtistURIs.isEmpty()) {
                System.out.println("Album Artist URIs:");
                AlbumArtistURIs = sc.nextLine();
            }
            tr.setAlbumArtistURIs(AlbumArtistURIs);

            System.out.println("Album Artist Names");
            String AlbumArtistNames = sc.nextLine();
            while (AlbumArtistNames.isEmpty()) {
                System.out.println("Album Artist Names:");
                AlbumArtistNames = sc.nextLine();
            }
            tr.setAlbumArtistNames(AlbumArtistNames);

            System.out.println("Album Release Date: (yyyy-mm-dd)");
            String AlbumReleaseDate = sc.nextLine();
            while (AlbumReleaseDate.isEmpty()) {
                System.out.println("Album Release Date: (yyyy-mm-dd)");
                AlbumReleaseDate = sc.nextLine();
            }
            tr.setAlbumReleaseDate(AlbumReleaseDate);

            System.out.println("Album Image URL:");
            String AlbumImageURL = sc.nextLine();
            while (AlbumImageURL.isEmpty()) {
                System.out.println("Album Image URL:");
                AlbumImageURL = sc.nextLine();
            }
            tr.setAlbumImageURL(AlbumImageURL);

            System.out.println("Disc Number:");
            String DiscNumber = sc.nextLine();
            while (DiscNumber.isEmpty()) {
                System.out.println("Disc Number:");
                DiscNumber = sc.nextLine();
            }
            tr.setDiscNumber(DiscNumber);

            System.out.println("Track Number:");
            String TrackNumber = sc.nextLine();
            while (TrackNumber.isEmpty()) {
                System.out.println("Track Number:");
                TrackNumber = sc.nextLine();
            }
            tr.setTrackNumber(TrackNumber);

            System.out.println("Track Duration:");
            String TrackDuration = sc.nextLine();
            while (TrackDuration.isEmpty()) {
                System.out.println("Track Duration:");
                TrackDuration = sc.nextLine();
            }
            tr.setTrackDuration(TrackDuration);

            System.out.println("Track Preview URL");
            String TrackPreviewURL = sc.nextLine();
            while (TrackPreviewURL.isEmpty()) {
                System.out.println("Track Preview URL:");
                TrackPreviewURL = sc.nextLine();
            }
            tr.setTrackPreviewURL(TrackPreviewURL);

            System.out.println("Es Explicit? Escriba 1 para Si, 0 para no");
            int opcion = sc.nextInt();
            while (opcion != 0 && opcion != 1) {
                System.out.println("Es Explicit? Escriba 1 para Si, 0 para no");
                opcion = sc.nextInt();
            }
            if (opcion == 1) {
                Boolean Explicit = true;
                tr.setExplicit(Explicit);
                sc.nextLine();

                System.out.println("Popularity:");
                String Popularity = sc.nextLine();
                while (Popularity.isEmpty()) {
                    System.out.println("Popularity:");
                    Popularity = sc.nextLine();
                }
                tr.setPopularity(Popularity);

                System.out.println("Added By:");
                String AddedBy = sc.nextLine();
                while (AddedBy.isEmpty()) {
                    System.out.println("Added By:");
                    AddedBy = sc.nextLine();
                }
                tr.setAddedBy(AddedBy);

                System.out.println("Added At:(yyyy-mm-dd)");
                String AddedAt = sc.nextLine();
                while (AddedAt.isEmpty()) {
                    System.out.println("Added At: (yyyy-mm-dd)");
                    AddedAt = sc.nextLine();
                }
                tr.setAddedAt(AddedAt);
            } else if (opcion == 0) {
                Boolean Explicit = false;
                tr.setExplicit(Explicit);
                sc.nextLine();
                System.out.println("Popularity:");
                String Popularity = sc.nextLine();
                while (Popularity.isEmpty()) {
                    System.out.println("Popularity:");
                    Popularity = sc.nextLine();
                }
                tr.setPopularity(Popularity);

                System.out.println("Added By:");
                String AddedBy = sc.nextLine();
                while (AddedBy.isEmpty()) {
                    System.out.println("Added By:");
                    AddedBy = sc.nextLine();
                }
                tr.setAddedBy(AddedBy);

                System.out.println("Added At: (yyyy-mm-dd)");
                String AddedAt = sc.nextLine();
                while (AddedAt.isEmpty()) {
                    System.out.println("Added At: (yyyy-mm-dd)");
                    AddedAt = sc.nextLine();
                }
                tr.setAddedAt(AddedAt);
            }
//Ejecuta un commit para materializar las peticiones.
            db.commit();
//Cierra la base de datos.
            db.close();
            System.out.println("\nHecho.");
        } catch (MtException mte) {
            System.out.println("MtException : " + mte.getMessage());
        }
    }
    // Imprime todos los valores de la BBDD Track
    public static void searchAll(String hostname, String dbname) {
        MtDatabase dbcon = new MtDatabase(hostname, dbname);
//Abre una conexión a la base de datos
        dbcon.open();
        try {
// Crea una instancia de Statement
            Statement statement = dbcon.createStatement();
// Asigna una consulta OQL. Utiliza REF() para obtener el objeto,
//directamente en vez de obtener valores concretos (que también podría ser).
            String commandText = "SELECT REF(a) from TrackList.track a;";
// Ejecuta la consulta y obtiene un ResultSet
            ResultSet resultset = statement.executeQuery(commandText);
            track track;
// Recorre el rset uno a uno
            while (resultset.next()) {
// Obtiene el objeto Track
                track = (track) resultset.getObject(1);
// Imprime los atributos de cada objeto en forma de tabla.
                System.out.println(
                        "TrackURI: " + String.format("%16s", track.getTrackURI())
                        + "\n TrackName: " + String.format("%16s ", track.getTrackName())
                        + "\n ArtistURIs: " + String.format("%16s ", track.getArtistURIs())
                        + "\n ArtistNames: " + String.format("%16s ", track.getArtistNames())
                        + "\n AlbumURI: " + String.format("%16s ", track.getAlbumURI())
                        + "\n AlbumName: " + String.format("%16s ", track.getAlbumName())
                        + "\n AlbumArtistURIs: " + String.format("%16s ", track.getAlbumArtistURIs())
                        + "\n AlbumArtistNames: " + String.format("%16s ", track.getAlbumArtistNames())
                        + "\n AlbumReleaseDate: " + String.format("%16s ", track.getAlbumReleaseDate())
                        + "\n AlbumImageURL: " + String.format("%16s ", track.getAlbumImageURL())
                        + "\n DiscNumber: " + String.format("%16s ", track.getDiscNumber())
                        + "\n TrackNumber: " + String.format("%16s ", track.getTrackNumber())
                        + "\n TrackDuration: " + String.format("%16s ", track.getTrackDuration())
                        + "\n TrackPreviewURL: " + String.format("%16s ", track.getTrackPreviewURL())
                        + "\n Explicit: " + String.format("%16s ", track.getExplicit())
                        + "\n Popularity: " + String.format("%16s ", track.getPopularity())
                        + "\n AddedBy: " + String.format("%16s ", track.getAddedBy())
                        + "\n AddedAT: " + String.format("%16s ", track.getAddedAt())
                        + "\n------------------------"
                );
            }
// Cierra las conexiones.
            resultset.close();
            statement.close();
            dbcon.close();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    // Imprime todas las canciones del artista a buscar
    public static void searchSongByArtist(String hostname, String dbname) {
        MtDatabase dbcon = new MtDatabase(hostname, dbname);
//Abre una conexión a la base de datos
        dbcon.open();
        Scanner sc = new Scanner(System.in);
        try {
            Statement statement = dbcon.createStatement();
            System.out.println("Buscar Canciones según Artista");
            String artist = sc.nextLine();
            String commandText = "SELECT REF(a) from TrackList.track a WHERE ArtistNames LIKE '" + artist + "%';";
            ResultSet resultset = statement.executeQuery(commandText);
            track track;
            while (resultset.next()) {
                track = (track) resultset.getObject(1);
                System.out.println("Cancion: " + String.format("%2s", track.getTrackName()) + " \n | Album: " + String.format("%2s", track.getAlbumName()) + "  \n | Autor: " + String.format("%16s", track.getArtistNames()));
            }
            resultset.close();
            statement.close();
            dbcon.close();
        } catch (SQLException e) {
            System.err.println(e);
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
    // Muestra solo los Albunes, Canciones y Autores
    public static void searchEverySong(String hostname, String dbname) {
        MtDatabase dbcon = new MtDatabase(hostname, dbname);
//Abre una conexión a la base de datos
        dbcon.open();
        Scanner sc = new Scanner(System.in);
        try {
            Statement statement = dbcon.createStatement();
            String commandText = "SELECT REF(a) from TrackList.track a";
            ResultSet resultset = statement.executeQuery(commandText);
            track track;
            while (resultset.next()) {
                track = (track) resultset.getObject(1);
                System.out.println("Cancion: " + String.format("%2s", track.getTrackName()) + " \n | Album: " + String.format("%2s", track.getAlbumName()) + "  \n | Autor: " + String.format("%16s", track.getArtistNames()));
            }
            resultset.close();
            statement.close();
            dbcon.close();
        } catch (SQLException e) {
            System.err.println(e);
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
    // Borra X Tracks de la BBDD
    public static void deleteSong(String hostname, String dbname) {
        System.out.println("=========== Borra objetos ==========\n");
        Scanner sc = new Scanner(System.in);
        try {
            MtDatabase db = new MtDatabase(hostname, dbname, new MtPackageObjectFactory("", "DAM"));
            db.open();
            db.startTransaction();
// Lista cuántos objetos Tracks con el método getInstanceNumber
            System.out.println("\n" + track.getInstanceNumber(db) + " Track(s) en la DB.");
//Crea un Iterador (propio de Java)
            MtObjectIterator<track> iter = track.<track>instanceIterator(db);
            System.out.println("Cuantas obras quieres borrar (Se borraran de arriba hacia abajo)");
            int numBorrar = sc.nextInt();
            while (iter.hasNext()) {
                track[] tracks = iter.next(numBorrar);
                System.out.println("Borrando " + tracks.length + " Obra(s)...");
                for (int i = 0; i < tracks.length; i++) {
//borra definitivamente el objeto
                    tracks[i].deepRemove();
                }
                break;
            }
            iter.close();
//materializa los cambios y cierra la BD
            db.commit();
            db.close();
            System.out.println("\n Borrado.");
        } catch (MtException mte) {
            System.out.println("MtException : " + mte.getMessage());
        }
    }
    // Modifica el AlbumName del AlbumName a buscar
    public static void modifySong(String hostname, String dbname) {
        System.out.println("=========== Modifica un objeto ==========\n");
        int nTrack = 0;
        Scanner sc = new Scanner(System.in);
        try {
            MtDatabase db = new MtDatabase(hostname, dbname, new MtPackageObjectFactory("", "DAM"));
            db.open();
            db.startTransaction();
// Lista cuántos objetos Track con el método getInstanceNumber
            System.out.println("\n" + track.getInstanceNumber(db) + " Tracks en la DB.");
            nTrack = (int) track.getInstanceNumber(db);
//Crea un Iterador (propio de Java)
            MtObjectIterator<track> iter = track.<track>instanceIterator(db);
            System.out.println("Album Name a buscar: ");
            String AlbumName = sc.nextLine();
            System.out.println("Album Name a modificar: (new AlbumName)");
            String newAlbumName = sc.nextLine();
            while (iter.hasNext()) {
                track[] tracks = iter.next(nTrack);
                for (int i = 0; i < tracks.length; i++) {
//Busca una Track con AlbumName 'AlbumName'
                    if (tracks[i].getAlbumName().compareTo(AlbumName) == 0) {
                        tracks[i].setAlbumName(newAlbumName);
                    }
                }
            }
            iter.close();
//materializa los cambios y cierra la BD
            db.commit();
            db.close();
            System.out.println("\n MODIFICADO.");
        } catch (MtException mte) {
            System.out.println("MtException : " + mte.getMessage());
        }
    }
}
