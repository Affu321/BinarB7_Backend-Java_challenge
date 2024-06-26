package com.Binar.Challenge4.Service;


import com.Binar.Challenge4.Entity.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {
    Map addProduct(Product product);
    Map updateProduct(UUID productId, Product product);
    Map deleteProduct(UUID productId);
    Map getProductById(UUID id);
    Map pagination(int page, int size);
    Optional<List<Product>> getAllProduct();
}