package epitech.backend.web.cart.service.impl;

import epitech.backend.domain.cart.model.Cart;
import epitech.backend.domain.cart.model.CartProduct;
import epitech.backend.domain.cart.service.CartService;
import epitech.backend.domain.product.model.Product;
import epitech.backend.domain.product.service.ProductService;
import epitech.backend.web.cart.representation.CartProductRepresentation;
import epitech.backend.web.cart.representation.CartRepresentation;
import epitech.backend.web.cart.service.WebCartService;
import epitech.backend.web.product.representation.ProductRepresentation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class WebCartServiceImpl implements WebCartService {

    private CartService cartService;
    private ProductService productService;

    @Inject
    public WebCartServiceImpl(CartService cartService, ProductService productService){
        this.cartService = cartService;
        this.productService = productService;
    }

    @Override
    public CartRepresentation createCart(CartRepresentation cartRepresentation) {
        int i = 0;
        ModelMapper modelMapper = new ModelMapper();
        Cart cart = new Cart();
        cart.setCartProducts(new ArrayList<>());
        for(CartProductRepresentation cartProductRepresentation : cartRepresentation.getCartProducts()){
            CartProduct cartProduct = new CartProduct();
            Product product = this.productService.getById(cartProductRepresentation.getProductRepresentation().getId());
            cartProduct.setProduct(product);
            cartProduct.setAmount(cartProductRepresentation.getAmount());
            cartProduct.setCart(cart);
            cart.getCartProducts().add(cartProduct);
            i++;
        }
        cart = this.cartService.createCart(cart);
        cartRepresentation.setId(cart.getId());
        cartRepresentation.setCartProducts(new ArrayList<>());
        for(CartProduct cartProduct : cart.getCartProducts()){
            CartProductRepresentation cartProductRepresentation = new CartProductRepresentation();
            cartProductRepresentation.setId(cartProduct.getId());
            cartProductRepresentation.setAmount(cartProduct.getAmount());
            cartProductRepresentation.setProductRepresentation(modelMapper.map(cartProduct.getProduct(), ProductRepresentation.class));
            cartRepresentation.getCartProducts().add(cartProductRepresentation);
        }
        return cartRepresentation;
    }

    @Override
    public CartRepresentation getCart(int idCart) {
        ModelMapper modelMapper = new ModelMapper();
        Cart cart = this.cartService.getCart(idCart);
        CartRepresentation cartRepresentation = modelMapper.map(cart, CartRepresentation.class);
        cartRepresentation.setCartProducts(new ArrayList<>());

        for(CartProduct cartProduct : cart.getCartProducts()){
            CartProductRepresentation cartProductRepresentation = new CartProductRepresentation();
            cartProductRepresentation.setId(cartProduct.getId());
            cartProductRepresentation.setAmount(cartProduct.getAmount());
            cartProductRepresentation.setProductRepresentation(modelMapper.map(cartProduct.getProduct(), ProductRepresentation.class));
            cartRepresentation.getCartProducts().add(cartProductRepresentation);
        }

        return cartRepresentation;
    }

    @Override
    public CartRepresentation addToCart(int idCart, CartProductRepresentation cartProductRepresentation) {
        ModelMapper modelMapper = new ModelMapper();
        Product product = this.productService.getById(cartProductRepresentation.getProductRepresentation().getId());
        Cart cart = this.cartService.getCart(idCart);
        List<CartProduct> cartProducts = cart.getCartProducts().stream().filter(cartProduct -> cartProduct.getProduct().getId() == product.getId()).collect(Collectors.toList());
        if(cartProducts.size() > 0 && cartProducts.get(0).getAmount() + cartProductRepresentation.getAmount() > -1){
            cartProducts.get(0).setAmount(cartProducts.get(0).getAmount() + cartProductRepresentation.getAmount());
        }
        else{
            CartProduct cartProduct = modelMapper.map(cartProductRepresentation, CartProduct.class);
            cartProduct.setCart(cart);
            cartProduct.setProduct(product);
            cart.getCartProducts().add(cartProduct);
        }
        cart = this.cartService.saveCart(cart);

        CartRepresentation cartRepresentation = modelMapper.map(cart, CartRepresentation.class);
        cartRepresentation.setCartProducts(new ArrayList<>());

        for(CartProduct cartProductFromCart : cart.getCartProducts()){
            cartProductRepresentation = new CartProductRepresentation();
            cartProductRepresentation.setId(cartProductFromCart.getId());
            cartProductRepresentation.setAmount(cartProductFromCart.getAmount());
            cartProductRepresentation.setProductRepresentation(modelMapper.map(cartProductFromCart.getProduct(), ProductRepresentation.class));
            cartRepresentation.getCartProducts().add(cartProductRepresentation);
        }

        return cartRepresentation;
    }

    @Override
    public CartRepresentation deleteFromCart(int idCart, CartProductRepresentation cartProductRepresentation) {
        ModelMapper modelMapper = new ModelMapper();
        Product product = this.productService.getById(cartProductRepresentation.getProductRepresentation().getId());
        Cart cart = this.cartService.getCart(idCart);
        List<CartProduct> cartProducts = cart.getCartProducts().stream().filter(cartProduct -> cartProduct.getProduct().getId() == product.getId()).collect(Collectors.toList());
        if(cartProducts.size() > 0){
            cart.getCartProducts().remove(cartProducts.get(0));
            cart = this.cartService.saveCart(cart);
            cart = this.cartService.saveCart(cart);
        }

        CartRepresentation cartRepresentation = modelMapper.map(cart, CartRepresentation.class);
        cartRepresentation.setCartProducts(new ArrayList<>());

        for(CartProduct cartProductFromCart : cart.getCartProducts()){
            cartProductRepresentation = new CartProductRepresentation();
            cartProductRepresentation.setId(cartProductFromCart.getId());
            cartProductRepresentation.setAmount(cartProductFromCart.getAmount());
            cartProductRepresentation.setProductRepresentation(modelMapper.map(cartProductFromCart.getProduct(), ProductRepresentation.class));
            cartRepresentation.getCartProducts().add(cartProductRepresentation);
        }

        return cartRepresentation;
    }
}