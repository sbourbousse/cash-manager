package epitech.backend.web.product.service.impl;

import epitech.backend.domain.product.model.Product;
import epitech.backend.domain.product.service.ProductService;
import epitech.backend.web.product.representation.ProductRepresentation;
import epitech.backend.web.product.service.WebProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class WebProductServiceImpl implements WebProductService {

    private ProductService productService;

    @Inject
    public WebProductServiceImpl(ProductService productService){
        this.productService = productService;
    }

    public List<ProductRepresentation> getAll(){
        ModelMapper modelMapper = new ModelMapper();
        List<Product> products;
        List<ProductRepresentation> productRepresentations = new ArrayList<>();
        products = this.productService.getAll();

        for(Product product : products){
            ProductRepresentation productRepresentation = modelMapper.map(product, ProductRepresentation.class);
            productRepresentations.add(productRepresentation);
        }
        return productRepresentations;
    }

    @Override
    public ProductRepresentation createProduct(ProductRepresentation productRepresentation) {
        ModelMapper modelMapper = new ModelMapper();
        Product product = modelMapper.map(productRepresentation, Product.class);
        product = this.productService.createProduct(product);
        productRepresentation = modelMapper.map(product, ProductRepresentation.class);
        return productRepresentation;
    }
}
