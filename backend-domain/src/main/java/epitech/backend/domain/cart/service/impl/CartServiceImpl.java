package epitech.backend.domain.cart.service.impl;

import epitech.backend.domain.cart.model.Cart;
import epitech.backend.domain.cart.model.CartProduct;
import epitech.backend.domain.cart.repository.CartRepository;
import epitech.backend.domain.cart.service.CartService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;

    @Inject
    public CartServiceImpl(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart createCart(Cart cart) {
        return this.cartRepository.save(cart);

    }

    @Override
    public List<Cart> getAll() {
        return this.cartRepository.findAll();
    }

    @Override
    public Cart getCart(int idCart) {
        return this.cartRepository.getOne(idCart);
    }

    @Override
    public float getTotalCart(int idCart){
        float total = 0;
        Cart cart = this.cartRepository.getOne(idCart);
        for(CartProduct cartProduct : cart.getCartProducts()){
            total += cartProduct.getAmount()*cartProduct.getProduct().getPrice();
        }

        return total;
    }

    @Override
    public void deleteCart(int idCart) {
        Cart cart = this.cartRepository.getOne(idCart);
        this.cartRepository.delete(cart);
    }

    @Override
    public Cart saveCart(Cart cart) {
        return this.cartRepository.save(cart);
    }
}
