package org.example.redsocial.dao;

import org.example.database.DbConnection;
import org.example.redsocial.model.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MessageDAO {

    private final DbConnection dbConnection;

    // Inyectar la dependencia de DbConnection en el constructor
    public MessageDAO(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }


    public void createMessageDB(Message mensaje) {
        String query = "INSERT INTO message (message, fullName,date) VALUES (?, ?,?)";

        // Obtener la conexión dentro del método
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, mensaje.getMessage());
            ps.setString(2, mensaje.getFullName());
            ps.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis())); // Asigna la fecha actual
            ps.executeUpdate();
            System.out.println("Mensaje creado correctamente.");

        } catch (SQLException e) {
            System.err.println("Error al crear mensaje: " + e.getMessage());
        }
    }

    public static void listMessagesDB(){

    }

    public static void deleteMessageDB(Message mensaje){

    }
    public static void updateMessageDB(Message mensajes){

    }
}
