package org.example;

import org.example.redsocial.service.MessageService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        MessageService messageService = new MessageService();
        Scanner sc = new Scanner(System.in);// Crea un solo Scanner para toda la aplicación

        int opcion = 0;

        do {
            System.out.println("------------------");
            System.out.println("Aplicacion de mensajes");
            System.out.println("1. Crear mensaje");
            System.out.println("2. Listar mensaje");
            System.out.println("3. Editar mensaje");
            System.out.println("4. Eliminar mensaje");
            System.out.println("5. Salir");

            // Lee la opción del usuario

            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
                sc.nextLine();// Consume el salto de línea después de nextInt()
            } else {
                System.out.println("Entrada no válida. Introduce un número.");
                sc.nextLine(); // Limpia la entrada no válida
                continue;
            }

            switch (opcion){
                case 1:
                    messageService.createMessage(sc);// Pasa el Scanner como parámetro
                    break;
                case 2:
                    MessageService.listMessages();
                    break;
                case 3:
                    MessageService.updateMessage();
                    break;
                case 4:
                    MessageService.deleteMessage();
                    break;
                default:
                    break;

            }

        }while (opcion !=5 );

        sc.close();
    }
}