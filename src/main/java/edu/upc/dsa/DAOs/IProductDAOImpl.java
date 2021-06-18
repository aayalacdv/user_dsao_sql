package edu.upc.dsa.DAOs;

import edu.upc.dsa.interfaces.IProductDAO;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.ShopProduct;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IProductDAOImpl implements IProductDAO {
    private Connection conn;
    private Statement stm;
    private ResultSet rs;

    public IProductDAOImpl(Connection conn){this.conn = conn; }

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

    private float getMoneyUser(String idUser){
        int money = 0;
        try {
            this.stm = conn.createStatement();
            String query = "SELECT * FROM user where idUser='" + idUser + "'";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                money = rs.getInt("money");
            }

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
        return money;
    }

    private float getPriceProduct(String idProduct){
        int price = 0;
        try {
            this.stm = conn.createStatement();
            String query = "SELECT * FROM product where idProduct='" + idProduct + "'";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                price = rs.getInt("price");
            }
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
                sql.printStackTrace();
            }
        }
        return price;
    }

    private void spendMoney(float cost, String idUser){
        try{
            this.stm = conn.createStatement();
            String query = "UPDATE user set money =" + cost + " where idUser ='" + idUser +"'";
            stm.executeUpdate(query);
        }catch(SQLException sql ){
            System.out.println(sql.getMessage());
        }
        finally {
            try{
                if (this.rs != null) {
                    this.rs.close();
                }
                if (this.stm!= null) {
                    this.stm.close();
                }
            } catch (SQLException sql){
                sql.printStackTrace();
            }
        }

    }

    private int cantUser(String idUser, String idProduct)
    {
        int total = 0;
        try {
            this.stm = conn.createStatement();
            String query = "SELECT cantidad FROM userProducts WHERE id_User='" + idUser + "' and id_Product='"+idProduct+"'";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int cant = rs.getInt("cantidad");
                total += cant;
            }
        } catch (SQLException sql) {
            System.out.println(sql.getMessage());

        } finally {
            try {
                if (this.rs != null) {
                    this.rs.close();
                }
                if (this.stm != null) {
                    this.stm.close();
                }
            } catch (SQLException sql) {

            }
        }

        return total;
    }
    @Override
    public Boolean buyProduct(ShopProduct p) {
        Boolean done = false;
        float price = getPriceProduct(p.getIdProduct());
        float money = getMoneyUser(p.getIdUser());
        float cost = p.getAmount() * price;
        int cantUser = cantUser(p.getIdUser(),p.getIdProduct());
        if (money >= cost) {
            if ( cantUser == 0) {
                try {
                    this.stm = conn.createStatement();
                    String query = "INSERT INTO userProducts VALUES ('"
                            + p.getIdUser() + "','"
                            + p.getIdProduct() + "',"
                            + p.getAmount() + ")";
                    stm.executeUpdate(query);
                    done = true;
                } catch (SQLException sql) {
                    System.out.println(sql.getMessage());

                } finally {
                    try {
                        if (this.rs != null) {
                            this.rs.close();
                        }
                        if (this.stm != null) {
                            this.stm.close();
                        }
                    } catch (SQLException sql) {

                    }
                }
            }
            else if (cantUser != 0)
            {
                int totalAmount = cantUser + p.getAmount();
                try{
                    this.stm = conn.createStatement();
                    String query = "UPDATE userProducts set cantidad=" + totalAmount +
                            " where id_User='" + p.getIdUser() +
                            "' and id_Product='"+p.getIdProduct()+"'";
                    stm.executeUpdate(query);
                    done = true;
                }catch(SQLException sql ){
                    System.out.println(sql.getMessage());

                } finally {
                    try {
                        if (this.rs != null) {
                            this.rs.close();
                        }
                        if (this.stm != null) {
                            this.stm.close();
                        }
                    } catch (SQLException sql) {

                    }
                }


            }
        }
        if (done)
        {
            float money2 = money - cost;
            spendMoney(money2, p.getIdUser());
        }
        return done;
    }

    @Override
    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        try {
            this.stm = conn.createStatement();
            String query = "SELECT * FROM product";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("idProduct");
                float price = rs.getFloat("price");
                String url = rs.getString("urlProduct");
                Product product = new Product(name, price, url);
                productList.add(product);
            }
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
        return productList;
    }

    @Override
    public List<ShopProduct> findProductsByUser(String userId) {
        List<ShopProduct> productsUserList = new ArrayList<>();
        try {
            this.stm = conn.createStatement();
            String query = "SELECT * FROM userProducts where id_User='" + userId + "'";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                String id_Product = rs.getString("id_Product");
                int cant = rs.getInt("cantidad");

                ShopProduct product = new ShopProduct(id_Product,cant);
                productsUserList.add(product);
            }
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
        return productsUserList;
    }
}
