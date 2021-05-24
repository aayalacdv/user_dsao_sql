package edu.upc.dsa.models;

import java.util.List;

public class Usuario {
    private String id;
    private String name;
    private String surname;
    private String playerId;
    private String password;
    private int edad;
    private float money;
    private List<Game> gamesList;
    private List<Producto> productoList;

    public Usuario(){}

    public Usuario(String id , String name, String surname, String password,String playerId, int  edad, float money){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.playerId = playerId;
        this.edad = edad;
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public List<Game> getGamesList() {
        return gamesList;
    }

    public void setGamesList(List<Game> gamesList) {
        this.gamesList = gamesList;
    }

    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", playerId='" + playerId + '\'' +
                ", password='" + password + '\'' +
                ", edad=" + edad +
                ", money=" + money +
                '}';
    }
}
