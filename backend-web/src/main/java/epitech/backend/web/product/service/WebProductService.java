package epitech.backend.web.product.service;

import epitech.backend.web.product.representation.ProductRepresentation;

import java.util.List;

public interface WebProductService {

    List<ProductRepresentation> getAll();

    ProductRepresentation createProduct(ProductRepresentation productRepresentation);
}
