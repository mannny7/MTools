package com.manny.mtools.playerdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {


    private final String HOST = "localhost";
    private final int PORT = 3306;
    private final String DATABASE = "mtools";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    private Connection connection;

    public void connect() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?useSSL=false",
                USERNAME,
                PASSWORD);
    }


    public boolean isConnected() { return connection != null; }

    // Will return a boolean for the connection status
    public Connection getConnection() { return connection; }

    // Will disconnect from the database if there is currently a connection
    public void disconnect() {
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
