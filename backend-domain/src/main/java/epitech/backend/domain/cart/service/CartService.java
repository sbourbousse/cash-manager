package epitech.backend.domain.cart.service;

import epitech.backend.domain.cart.model.Cart;

import java.util.List;

public interface CartService {

    Cart createCart(Cart cart);

    List<Cart> getAll();

    Cart getCart(int idCart);

    float getTotalCart(int idCart);

    void deleteCart(int idCart);

    Cart saveCart(Cart cart);
}
