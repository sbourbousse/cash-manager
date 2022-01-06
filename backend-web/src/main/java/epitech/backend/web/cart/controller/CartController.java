package epitech.backend.web.cart.controller;

import epitech.backend.web.cart.representation.CartProductRepresentation;
import epitech.backend.web.cart.representation.CartRepresentation;
import epitech.backend.web.cart.service.WebCartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    private WebCartService webCartService;

    @Inject
    public CartController(WebCartService webCartService){
        this.webCartService = webCartService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<CartRepresentation> createCart(@RequestBody CartRepresentation cartRepresentation){
        cartRepresentation = this.webCartService.createCart(cartRepresentation);
        return new ResponseEntity<>(cartRepresentation, HttpStatus.OK);
    }

    @GetMapping("/{idCart}")
    @ResponseBody ResponseEntity<List<CartProductRepresentation>> getCart(@PathVariable("idCart") int idCart){
        CartRepresentation cartRepresentation = this.webCartService.getCart(idCart);

        return new ResponseEntity<>(cartRepresentation.getCartProducts(), HttpStatus.OK);
    }

    @PutMapping("/add/{idCart}")
    @ResponseBody ResponseEntity<CartRepresentation> addProductToCart(@PathVariable("idCart") int idCart, @RequestBody CartProductRepresentation cartProductRepresentation){
        CartRepresentation cartRepresentation = this.webCartService.addToCart(idCart, cartProductRepresentation);

        return new ResponseEntity<>(cartRepresentation, HttpStatus.OK);
    }

    @PutMapping("/delete/{idCart}")
    @ResponseBody ResponseEntity<CartRepresentation> deleteProductToCart(@PathVariable("idCart") int idCart, @RequestBody CartProductRepresentation cartProductRepresentation){
        CartRepresentation cartRepresentation = this.webCartService.deleteFromCart(idCart, cartProductRepresentation);

        return new ResponseEntity<>(cartRepresentation, HttpStatus.OK);

    }
}