package proj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBSingleton {
    private static DBSingleton dbInstance;
    private final String SCHEMAUSER = "dba";
    private final String SCHEMAPASS = "sql123";
    private final String SCHEMACONNSTR = "jdbc:mysql://localhost:3306/musicalbums";
    public Connection dbConnection;

    private DBSingleton(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(SCHEMACONNSTR, SCHEMAUSER, SCHEMAPASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static DBSingleton getInstance() {
        return null == dbInstance ? new DBSingleton() : dbInstance;
    }
}
