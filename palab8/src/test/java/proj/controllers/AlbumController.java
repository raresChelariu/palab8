package proj.controllers;

import proj.DBSingleton;
import proj.exceptions.AlbumNotFound;
import proj.exceptions.ArtistNotFound;
import proj.models.Album;
import proj.models.Artist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlbumController {
    DBSingleton db;
    public AlbumController() {
        db = DBSingleton.getInstance();
    }
    public void create(String name, int artistId, int releaseYear) throws SQLException {
        Statement statement = db.dbConnection.createStatement();
        String query = String.format("INSERT INTO albums (name, artist_id, release_year) VALUES ('%s', %d, %d)", name, artistId, releaseYear);
        int rowsInserted = statement.executeUpdate(query);
        System.out.println(String.format("Number of rows inserted: %d", rowsInserted));
    }
    public int getAlbumID(String albumName, String artistName) throws SQLException {
        ArtistController artistDAO = new ArtistController();
        Artist singer = artistDAO.findByName(artistName);
        if (null == singer)
            throw new ArtistNotFound(artistName);
        Statement statement = db.dbConnection.createStatement();
        String query = String.format("SELECT % FROM albums WHERE artist_id=%d AND name='%s'", singer.getId(), albumName);
        ResultSet set = statement.executeQuery(query);
        if (set.next())
            return set.getInt(1);
        throw new AlbumNotFound(albumName);
    }
    public List<Album> findByArtist(int artistID) throws NullPointerException, SQLException {
        ArrayList<Album> albums = new ArrayList<Album>();
        Statement statement = db.dbConnection.createStatement();
        String query = String.format("SELECT * FROM albums WHERE artist_id=%d", artistID);
        ResultSet set = statement.executeQuery(query);
        while (set.next()) {
            albums.add(new Album(set.getInt(1), set.getInt(2), set.getInt(3),
                    set.getString(4)));
        }
        return albums.isEmpty() ? null : albums;
    }
}
