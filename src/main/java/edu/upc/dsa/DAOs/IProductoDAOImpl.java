package edu.upc.dsa.DAOs;

import edu.upc.dsa.interfaces.IProductoDAO;
import edu.upc.dsa.models.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class IProductoDAOImpl implements IProductoDAO {
    private Connection conn;
    private Statement stm;
    private ResultSet rs;
    private static IProductoDAO instance;


    public IProductoDAOImpl() {

    }

    public IProductoDAOImpl(Connection conn){this.conn = conn; }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Statement getStm() {
        return stm;
    }

    public void setStm(Statement stm) {
        this.stm = stm;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    @Override
    public Product getProductById(String id, String user) {
        Product product = null;
        try {
            this.stm = conn.createStatement();
            String query = "SELECT * FROM producto WHERE id='" + id + "'";
            ResultSet rs = stm.executeQuery(query);
            float price = rs.getFloat("price");
            product = new Product(id, price);

        } catch (SQLException sql) {
            System.out.println(sql.getMessage());

        } finally {
            try {
                if (this.stm != null) {
                    this.stm.close();
                }
                if(this.rs != null){
                    this.rs.close();
                }

            } catch (SQLException sql) {

            }
        }
        return product;
    }

    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public List<Product> getProductsByUser(String userId) {
        return null;
    }

    public static IProductoDAO getInstance() {
        if (instance==null)
        {
            instance = new IProductoDAOImpl();
        }
        return instance;
    }
}
