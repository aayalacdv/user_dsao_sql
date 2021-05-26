package edu.upc.dsa.models;

public class Buys {
    Product product;
    User user;
    int num;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Buys{" +
                "product=" + product +
                ", user=" + user +
                ", num=" + num +
                '}';
    }
}
