package org.example;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Conexion conexion = new Conexion();

        try (Connection cnx = conexion.get_connection()){

        }catch (Exception e){

            System.out.println(e);

        }
    }
}