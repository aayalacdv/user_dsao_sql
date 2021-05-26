package edu.upc.dsa.interfaces;

import edu.upc.dsa.models.Product;

import java.util.List;

public interface IProductoDAO {
    public Product getProductById(String id, String userId);
    public List<Product> getProducts();
    public List<Product> getProductsByUser(String userId);
}
