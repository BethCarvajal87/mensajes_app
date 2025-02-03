package org.example.database;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static  org.example.DataBase.*;

public class DbConnection {
    private static DbConnection instance;
    private Connection connection;

    private DbConnection() throws SQLException {
        try {
            connection = DriverManager.getConnection(URL + DB, USER, PASSWORD);
            System.out.println("Conexión a la base de datos exitosa");
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            throw e; // Relanzar la excepción para que el llamador la maneje
        }
    }

    public static synchronized DbConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DbConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
