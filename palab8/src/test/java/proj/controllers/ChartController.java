package proj.controllers;

import proj.DBSingleton;
import proj.models.Artist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ChartController {
    DBSingleton db;
    public ChartController() {
        db = DBSingleton.getInstance();
    }
    public void createChart(String name, String date) throws SQLException {
        Statement statement = db.dbConnection.createStatement();
        String query = String.format("INSERT INTO chartdate (name, date) VALUES ('%s', '%s')", name, date);
        int rowsInserted = statement.executeUpdate(query);
        System.out.println(String.format("[Table %s]Number of rows inserted: %d", "chartdate", rowsInserted));
    }
    public void insertRank(int chartID, int albumID, int rank) throws SQLException{
        Statement statement = db.dbConnection.createStatement();
        String query = String.format("INSERT INTO chartrank (idChart, idAlbum, rank) VALUES (%d, %d, %d)",
                chartID,
                albumID,
                rank);
        int rowsInserted = statement.executeUpdate(query);
        System.out.println(String.format("[Table %s]Number of rows inserted: %d", "chartrank", rowsInserted));
    }

    public int getChartID(String name, String date) throws SQLException{
        Statement statement = db.dbConnection.createStatement();
        String query = String.format("SELECT * FROM chartdate WHERE name='%s' AND date='%s'", name, date);
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next())
            return resultSet.getInt(1);
        return -1;
    }
}
