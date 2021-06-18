package edu.upc.dsa.interfaces;

import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.ShopProduct;

import java.util.List;

public interface IProductDAO {
    public Boolean buyProduct(ShopProduct purchase);
    public List<Product> findAll();
    public List<ShopProduct> findProductsByUser(String userId);
}