package epitech.backend.web.cart.service;

import epitech.backend.web.cart.representation.CartProductRepresentation;
import epitech.backend.web.cart.representation.CartRepresentation;

import java.util.List;

public interface WebCartService {
    CartRepresentation createCart(CartRepresentation cartRepresentation);

    CartRepresentation getCart(int idCart);

    CartRepresentation addToCart(int idCart, CartProductRepresentation cartProductRepresentation);

    CartRepresentation deleteFromCart(int idCart, CartProductRepresentation cartProductRepresentation);
}
