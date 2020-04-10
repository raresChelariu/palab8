package proj.controllers;


import proj.DBSingleton;
import proj.models.Artist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ArtistController {
    DBSingleton db;
    public ArtistController() {
        db = DBSingleton.getInstance();
    }
    public void create(String name, String country) throws SQLException {
        Statement statement = db.dbConnection.createStatement();
        String query = String.format("INSERT INTO artists (name, country) VALUES ('%s', '%s')", name, country);
        int rowsInserted = statement.executeUpdate(query);
        System.out.println(String.format("Number of rows inserted: %d", rowsInserted));
    }
    public Artist findByName(String name) throws NullPointerException, SQLException {
        Artist lastArtist = null;
        Statement statement = db.dbConnection.createStatement();
        String query = String.format("SELECT * FROM artists WHERE name='%s'", name);
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            lastArtist = new Artist(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
            System.out.println(lastArtist);
        }
        return lastArtist;
    }
}
