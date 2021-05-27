package edu.upc.dsa;

import edu.upc.dsa.DAOs.IUserDAOImpl;
import edu.upc.dsa.interfaces.Factory;
import edu.upc.dsa.models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class FactoryImpl implements Factory {

    private static FactoryImpl instance;
    private Connection connection;
    private IUserDAOImpl userDAO;

    private FactoryImpl(){
        connectDatabase();
        //intit DAOS
        this.userDAO = new IUserDAOImpl(this.connection);
    }

    public static FactoryImpl getInstance(){
        if(instance == null) {
            instance = new FactoryImpl();
        }
        return instance;
    }


    @Override
    public void connectDatabase() {
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
                    "root", "mysql");
            boolean valid = connection.isValid(50000);
            System.out.println(valid ? "TEST OK" : "TEST FAIL");
        } catch (java.sql.SQLException sqle) {
            System.out.println("Error: " + sqle);
        }
    }

    @Override
    public Object findById(String id, String obj) {
        switch (obj){
            case "usuario":
                return this.userDAO.findUsuariobyId(id);
            case "producto":
                return null;
            case "game":
                return null;
        }
        return null;
    }

    @Override
    public List<Object> findAll(String obj) {
        switch(obj){
            case "usuario":
                List<User> users = this.userDAO.findAll();
                List<Object> objects = new ArrayList<>();
                objects.addAll(users);
                return objects;
            case "producto":
                return null;
            case "game":
                return null;

        }
        return null;
    }

    @Override
    public void save(Object obj) {
       String model = obj.getClass().toString();

       switch (model) {
           case "class edu.upc.dsa.models.User":
               User user = (User)obj;
               userDAO.addUsuario(user);
               break;
           case "class edu.upc.dsa.models.Product":
               break;
           case "class edu.upc.dsa.models.Game":
               break;
           default:

       }

    }

    @Override
    public void delete(Object obj) {

    }

    @Override
    public void update(Object obj) {

    }
}
