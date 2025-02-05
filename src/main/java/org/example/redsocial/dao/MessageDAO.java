package org.example.redsocial.dao;

import org.example.database.DbConnection;
import org.example.redsocial.model.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {

    private final DbConnection dbConnection;

    // Inyectar la dependencia de DbConnection en el constructor
    public MessageDAO(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }


    public void createMessageDB(Message mensaje) {
        String query = "INSERT INTO message (message, fullName,date) VALUES (?, ?,?)";

        // Obtener la conexión dentro del método
        try {

            Connection connection = dbConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, mensaje.getMessage());
            ps.setString(2, mensaje.getFullName());
            ps.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis())); // Asigna la fecha actual
            ps.executeUpdate();
            System.out.println("Mensaje creado correctamente.");

        } catch (SQLException e) {
            System.err.println("Error al crear mensaje: " + e.getMessage());
        }
    }

    public List<Message> listMessagesDB(){
        List<Message> messagesList = new ArrayList();
        String query = "select * from message";

    try{

        Connection connection = dbConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            Message message = new Message();
            message.setMessageId(rs.getInt(1));
            message.setMessage(rs.getString(2));
            message.setFullName(rs.getString(3));
            message.setDate(rs.getString(4));
            messagesList.add(message);
        }

       }catch (SQLException e) {
       System.err.println("Error al crear mensaje: " + e.getMessage());
    }

    return messagesList;
    }

    public void deleteMessageDB(int messageId){

        String query = "delete from message where messageId = ?";

        try{
            Connection connection = dbConnection.getConnection();
            PreparedStatement ps  = connection.prepareStatement(query);
            ps .setInt(1, messageId);
            int countRowsUpdated = ps .executeUpdate();
            if (countRowsUpdated != 0) {
                System.out.println("El mensaje con id " + messageId + " ha sido eliminado correctamente.");
            } else {
                System.out.println("El mensaje con id " + messageId + " no fue encontrado.");
            }


        }catch (SQLException e) {
            System.err.println("No se pudo eliminar el mensaje" + e.getMessage());

        }

    }

    public void updateMessageDB(Message mensaje){
        StringBuilder query = new StringBuilder("UPDATE message SET ");
        List<Object> params = new ArrayList<>();
        boolean hasUpdates = false;

        if (mensaje.getMessage() != null) {
            query.append("message = ?, ");
            params.add(mensaje.getMessage());
            hasUpdates = true;
        }

        if (mensaje.getFullName() != null) {
            query.append("fullName = ?, ");
            params.add(mensaje.getFullName());
            hasUpdates = true;
        }

        if (mensaje.getDate() != null) {
            query.append("date = ?, ");
            params.add(new java.sql.Timestamp(System.currentTimeMillis()));
            hasUpdates = true;
        }

        if (!hasUpdates) {
            System.out.println("No se proporcionaron campos para actualizar.");
            return;
        }

        query.setLength(query.length() - 2);

        query.append(" WHERE messageId = ?");
        params.add(mensaje.getMessageId());


        try{
            Connection connection = dbConnection.getConnection();
            PreparedStatement ps  = connection.prepareStatement(query.toString());

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            int countRowsUpdated = ps.executeUpdate();

            if (countRowsUpdated != 0) {
                System.out.println("El mensaje con id " + mensaje.getMessageId() + " ha sido actualizado correctamente.");
            } else {
                System.out.println("El mensaje con id " + mensaje.getMessageId() + " no fue encontrado.");
            }

        }catch (SQLException e) {
            System.err.println("No se pudo eliminar el mensaje" + e.getMessage());

        }


    }

    public Message searchMessageById(int messageId) {

        Message message = null;
        String query = "select * from message where messageId = ?  ";

        try {

            Connection connection = dbConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, messageId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                message = new Message();
                message.setMessageId(rs.getInt(1));
                message.setMessage(rs.getString(2));
                message.setFullName(rs.getString(3));
                message.setDate(rs.getString(4));
            }
            if (message ==null){
                System.out.println("No se ha encontrado el mensaje con el "+ message.getMessageId());
            }else {
                System.out.println("Se encontro el mensaje con Id: " + message);
            }


        } catch (SQLException e) {
            System.err.println("Error al crear mensaje: " + e.getMessage());
        }
        return message;
    }
}
