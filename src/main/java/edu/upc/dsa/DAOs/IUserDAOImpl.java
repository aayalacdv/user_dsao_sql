package edu.upc.dsa.DAOs;
import edu.upc.dsa.interfaces.IUserDAO;
import edu.upc.dsa.models.Usuario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    public Usuario findUsuariobyId(String id) {
        Usuario user = null;
        try {
            this.stm = conn.createStatement();
            String query = "SELECT * FROM usuario WHERE id='" + id + "'";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("_name");
                String surname = rs.getString("surname");
                String password = rs.getString("_password");
                int edad = rs.getInt("edad");
                String playerId = rs.getString("player_id");
                float money = rs.getFloat("money");
                user = new Usuario(id, name, surname, password, playerId, edad , money);
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
    public List<Usuario> findAll() {
        try{
            this.stm = this.conn.createStatement();
            String query = "select * from usuario";
            this.rs = stm.executeQuery(query);
            List<Usuario> users = new ArrayList<>();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("_name");
                String surname = rs.getString("surname");
                String password = rs.getString("_password");
                int edad = rs.getInt("edad");
                String playerId = rs.getString("player_id");
                float money = rs.getFloat("money");
                Usuario u = new Usuario(id, name, surname, password, playerId, edad , money);
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
    public void addUsuario(Usuario usuario) {
        try{
            this.stm = conn.createStatement();
            String query = "INSERT INTO usuario VALUES ('"
                    +usuario.getId()+"','"
                    +usuario.getName()+"','"
                    +usuario.getSurname()+"','"
                    +usuario.getPassword()+"',"
                    +usuario.getEdad()+",'"
                    +usuario.getPlayerId()+"',"
                    +usuario.getMoney()+")";
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
    public void deleteUsuario(String id) {

    }

    @Override
    public void updateUsuario(String id, Usuario usuario) {

    }
}
