package epitech.backend.domain.product.service;

import epitech.backend.domain.product.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();

    Product createProduct(Product product);
    Product getById(Integer id);
}
