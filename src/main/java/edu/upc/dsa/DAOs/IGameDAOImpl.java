package edu.upc.dsa.DAOs;

import edu.upc.dsa.interfaces.IGameDAO;
import edu.upc.dsa.models.Game;
import edu.upc.dsa.models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IGameDAOImpl implements IGameDAO {

    private Connection conn;
    private Statement stm;
    private ResultSet rs;

    public IGameDAOImpl(Connection conn){this.conn = conn;}

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
    public void add(Game game) {
        //insertar juego en la base de datos de juegos el juego correspondiente
        try{
            this.stm = conn.createStatement();
            String query = "INSERT INTO game VALUES ('"
                    + game.getId()+"','"
                    + game.getDate()+"',"
                    + game.getDuration()+","
                    + game.getLevelsPassed()+",'"
                    + game.getIdPlayer()+"')";
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
                System.out.println(sql.getMessage());
            }
        }

    }

    @Override
    public void endGame(Game game) {
        try{
            this.stm = conn.createStatement();
            String query = "UPDATE game SET idGame = '"
                    + game.getId()+"', date = '"
                    + game.getDate()+"', duration = "
                    + game.getDuration()+", levels_passed = "
                    + game.getLevelsPassed()+", player_id = '"
                    + game.getIdPlayer()+"' where idGame = '"+ game.getId() +"'";

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
    public String getMap(int level ) {
       String levelPath = "";
        try {
            this.stm = conn.createStatement();
            String query = "SELECT prefab FROM unitygame WHERE level =" + level + "";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
              levelPath = rs.getString("prefab");
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
        return levelPath;

}


}
