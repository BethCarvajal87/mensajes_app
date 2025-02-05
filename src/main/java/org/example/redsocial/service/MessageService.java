package org.example.redsocial.service;

import org.example.database.DbConnection;
import org.example.redsocial.dao.MessageDAO;
import org.example.redsocial.model.Message;

import java.sql.SQLException;
import java.util.Scanner;

public class MessageService {

    private final MessageDAO messageDAO;
    public MessageService() throws SQLException {
        DbConnection dbConnection = DbConnection.getInstance();
        this.messageDAO = new MessageDAO(dbConnection);
    }

    public void createMessage(Scanner sc) {// Recibe el Scanner como parámetro

        try {
            System.out.println("Escribe tu mensaje: ");
            String message = sc.nextLine();

            System.out.println("Escribe tu nombre: ");
            String fullName = sc.nextLine();

            Message register = new Message();
            register.setMessage(message);
            register.setFullName(fullName);

            messageDAO.createMessageDB(register);

            } catch (Exception e) {
            System.err.println("Error al leer la entrada: " + e.getMessage());
        }
    }

    public void listMessages(){

        for (Message message : messageDAO.listMessagesDB()){
            System.out.println("");
            System.out.println("ID: " + message.getMessageId());
            System.out.println("Mensaje: " + message.getMessage());
            System.out.println("Autor: " + message.getFullName());
            System.out.println("Fecha: " + message.getDate());

        }

    }

    public void deleteMessage(Scanner sc){
        System.out.println("Indica el ID que deseas borrar:  ");
        int messageId = sc.nextInt();
        messageDAO.deleteMessageDB(messageId);

    }

    public void searchMessageById(Scanner sc){
        System.out.println("Indica el ID que deseas buscar:  ");
        int messageId = sc.nextInt();
        messageDAO.searchMessageById(messageId);

    }
    public void updateMessage(Scanner sc) {
        try {
            System.out.println("Indica el ID del mensaje que deseas actualizar: ");
            int messageId = sc.nextInt();
            sc.nextLine();

            System.out.println("Escribe el nuevo texto del mensaje: ");
            String newMessage = sc.nextLine();

            System.out.println("Escribe el nuevo nombre : ");
            String newFullName = sc.nextLine();

            Message updatedMessage = new Message();
            updatedMessage.setMessageId(messageId);
            updatedMessage.setMessage(newMessage);
            updatedMessage.setFullName(newFullName);

            messageDAO.updateMessageDB(updatedMessage);

        } catch (Exception e) {
            System.err.println("Error al actualizar el mensaje: " + e.getMessage());
        }
    }

}
