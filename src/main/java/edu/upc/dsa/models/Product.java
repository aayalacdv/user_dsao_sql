package edu.upc.dsa.models;

public class Product {
    private String id;
    private float price;
    private String url;


    public Product(){};

    public Product(String id, float price, String url){
        this.id = id;
        this.price = price;
        this.url = url;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id='" + id + '\'' +
                ", price=" + price +
                '\'' +
                '}';
    }
}
