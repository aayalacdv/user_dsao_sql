package edu.upc.dsa.models;

public class ShopProduct {
    private String idProduct;
    private String idUser;
    private int amount;

    public ShopProduct(String idProduct, int amount) {
        this.idProduct = idProduct;
        this.amount = amount;
    }

    public ShopProduct(String idProduct, String idUser, int amount) {
        this.idProduct = idProduct;
        this.idUser = idUser;
        this.amount = amount;
    }

    public ShopProduct() {
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "UserProducts{" +
                "idProduct='" + idProduct + '\'' +
                ", idUser='" + idUser + '\'' +
                ", amount=" + amount +
                '}';
    }
}
