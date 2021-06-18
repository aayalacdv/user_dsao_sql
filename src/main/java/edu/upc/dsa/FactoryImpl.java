package edu.upc.dsa;

import edu.upc.dsa.DAOs.IProductDAOImpl;
import edu.upc.dsa.DAOs.IUserDAOImpl;
import edu.upc.dsa.interfaces.Factory;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class FactoryImpl implements Factory {

    private static FactoryImpl instance;
    private Connection connection;
    private IUserDAOImpl userDAO;
    private IProductDAOImpl productDAO;

    private FactoryImpl(){
        connectDatabase();
        //intit DAOS
        this.userDAO = new IUserDAOImpl(this.connection);
        this.productDAO = new IProductDAOImpl(this.connection);
    }

    public static FactoryImpl getInstance(){
        if(instance == null) {
            instance = new FactoryImpl();
        }
        return instance;
    }

    public Connection getConnection(){ return this.connection; };


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
    public Object findById(String id, Object obj) {
        Object DAO = null;
        try{

            //objtenemos la clase del objeto
            String daoName = getDAOname(obj.getClass().getSimpleName());
            //hacemos un constructor para el DAO y le pasamos la conexión con la base de datos
            Class cls = Class.forName(daoName);
            Constructor constructor = cls.getConstructor( Connection.class);
            DAO = constructor.newInstance(this.connection);
            Method getById = cls.getMethod("findById", String.class);
            return getById.invoke(DAO,id);

        }catch (Exception e){
            e.printStackTrace();
        }

        return DAO;

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
                List<Product> productList = this.productDAO.findAll();
                List<Object> products = new ArrayList<>();
                products.addAll(productList);
                return (List<Object>)(Object)productList;
            case "game":
                return null;

        }
        return null;
    }

    @Override
    public void save(Object obj) {
        Object DAO = null;
       try{

            //objtenemos la clase del objeto
            String daoName = getDAOname(obj.getClass().getSimpleName());
            //hacemos un constructor para el DAO y le pasamos la conexión con la base de datos
            Class cls = Class.forName(daoName);
            System.out.println(cls);
            Constructor constructor = cls.getConstructor( Connection.class);
            DAO = constructor.newInstance(this.connection);
            Method addMethod = cls.getMethod("add",obj.getClass());
            addMethod.invoke(DAO,obj);
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void delete(Object obj) {

    }

    @Override
    public void update(Object obj) {

    }


    public String getDAOname(String cls){
        return "edu.upc.dsa.DAOs.I"+cls+"DAOImpl";
    }


}
