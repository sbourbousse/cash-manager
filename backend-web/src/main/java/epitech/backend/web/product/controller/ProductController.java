package epitech.backend.web.product.controller;

import epitech.backend.web.product.representation.ProductRepresentation;
import epitech.backend.web.product.service.WebProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private WebProductService webProductService;

    @Inject
    public ProductController(WebProductService webProductService) {
        this.webProductService = webProductService;
    }


    /**
     * Méthode de récupération de tous les produits
     *
     * @return Liste des produits disponibles
     */

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<ProductRepresentation>> getAllProducts(){

        List<ProductRepresentation> productRepresentations;
        productRepresentations = this.webProductService.getAll();
        return new ResponseEntity<>(productRepresentations, HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ProductRepresentation> createProduct(@RequestBody ProductRepresentation productRepresentation){
        productRepresentation = this.webProductService.createProduct(productRepresentation);

        return new ResponseEntity<>(productRepresentation, HttpStatus.OK);
    }
}