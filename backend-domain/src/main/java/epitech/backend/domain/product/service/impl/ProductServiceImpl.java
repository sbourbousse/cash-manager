package epitech.backend.domain.product.service.impl;

import epitech.backend.domain.product.model.Product;
import epitech.backend.domain.product.repository.ProductRepository;
import epitech.backend.domain.product.service.ProductService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Inject
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getAll(){
        return this.productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        product = this.productRepository.save(product);
        return product;
    }

    @Override
    public Product getById(Integer id){
        return this.productRepository.getOne(id);
    }
}