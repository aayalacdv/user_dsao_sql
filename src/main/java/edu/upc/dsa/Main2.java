package edu.upc.dsa;

import edu.upc.dsa.DAOs.IUserDAOImpl;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main2 {
    private static Connection connection;

    public static void connectDatabase() {
        try {
            // We register the MySQL (MariaDB) driver
            // Registramos el driver de MySQL (MariaDB)
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de MySQL: " + ex);
            }

            // Database connect
            // Conectamos con la base de datos
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/dsa",
                    "root",
                    "mysql");
            boolean valid = connection.isValid(50000);
            System.out.println(valid ? "TEST OK" : "TEST FAIL");
        } catch (java.sql.SQLException sqle) {
            System.out.println("Error: " + sqle);
        }
    }


    public  static void main(String[] args) throws ClassNotFoundException {




        //la idea es que cada DAO tiene  que interactuar con la base de dator por medio de la factoría
        //la menera de hacerlo es con un objeto llamado session que lo que nos permite es simplemente abrir una conexión a la base de datos cuando se le llame
        //Session tiene que ser una clase que obtenga los parámetros y creee la conexión a la base de datos y también por ejemplo determine que clase de objeto estamos  parseando
        //algo que podemos hacer para poder ayudarnos es simplemente crear la clase que nos permita crear un query genérico para insertar, borrar, y modificar, obtener todos los objetos,y enocntrar uno en concreto
        //entonces cuál es el valor de la factoría
        connectDatabase();
        IUserDAOImpl userDAO = new IUserDAOImpl( connection);
        System.out.println(userDAO.getClass().getSimpleName());
        Class cls = Class.forName("edu.upc.dsa.DAOs.IUserDAOImpl");















    }






}

