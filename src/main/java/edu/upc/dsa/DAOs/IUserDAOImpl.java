package edu.upc.dsa.DAOs;
import edu.upc.dsa.interfaces.IUserDAO;
import edu.upc.dsa.models.User;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class IUserDAOImpl implements IUserDAO {
    private Connection conn;
    private Statement stm;
    private ResultSet rs;

    public IUserDAOImpl(Connection conn){this.conn = conn; }

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
    public User findById(String id) {
        User user = null;
        try {
            this.stm = conn.createStatement();
            String query = "SELECT * FROM user WHERE idUser='" + id + "'";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String password = rs.getString("password");
                int age = rs.getInt("age");
                String playerId = rs.getString("player_id");
                float money = rs.getFloat("money");
                user = new User(id, name, surname, password, playerId, age , money);
                //TODO implementar DAO de otras estructuras para rellenar la lista de usuario
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
        return user;
    }

    @Override
    public List<User> findAll() {
        try{
            this.stm = this.conn.createStatement();
            String query = "select * from user";
            this.rs = stm.executeQuery(query);
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                String id = rs.getString("idUser");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String password = rs.getString("password");
                int age = rs.getInt("age");
                String playerId = rs.getString("player_id");
                float money = rs.getFloat("money");
                User u = new User(id, name, surname, password, playerId, age , money);
                users.add(u);
            }
            return users;

        }catch (SQLException sql ){
            System.out.println(sql.getMessage());
        }
        finally{
            try{
                if (this.rs != null) {
                    this.rs.close();
                }
                if (this.stm!= null) {
                    this.stm.close();
                }
            } catch (SQLException sql){
            }

        }
        return null;
    }

    @Override
    public void add(User user) {
        try{
            this.stm = conn.createStatement();
            String query = "INSERT INTO user VALUES ('"
                    + user.getId()+"','"
                    + user.getName()+"','"
                    + user.getSurname()+"', MD5('"
                    + user.getPassword()+"'),"
                    + user.getEdad()+",'"
                    + user.getPlayerId()+"',"
                    + user.getMoney()+")";
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

            }
        }
    }

    @Override
    public void delete(String id, String pass) {
        try {
            this.stm = conn.createStatement();
            String query = "SELECT password FROM user WHERE idUser='" + id + "'";
            ResultSet rs = stm.executeQuery(query);
            if (rs.equals(pass)) {
                try{
                    this.stm = conn.createStatement();
                    String query1 = "DELETE FROM user WHERE idUser = '" + id + "'";
                    stm.executeUpdate(query);
                }catch(SQLException sql ){
                    System.out.println(sql.getMessage());
                }
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
    }

    @Override
    public void update(String id, User user) {
        try{
            this.stm = conn.createStatement();
            String query = "UPDATE user SET idUser = '"
                    + user.getId()+"', name = '"
                    + user.getName()+"', surname = '"
                    + user.getSurname()+"', password = MD5('"
                    + user.getPassword()+"'), age = "
                    + user.getEdad()+", player_id = '"
                    + user.getPlayerId()+"' where id = '"+ id +"')";

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

            }
        }

    }

    public boolean compareMd5( String pwd,String hash ) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pwd.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter
                .printHexBinary(digest).toLowerCase(Locale.ROOT);
        return myHash.equals(hash);

    }

}
