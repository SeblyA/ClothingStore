package org.yearup.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.models.CartItem;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.repository.ShoppingCartRepository;

import java.util.List;

@Service
public class ShoppingCartService
{
    // a shopping cart is built from cart rows plus a product lookup for each row
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductService productService;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ProductService productService)
    {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productService = productService;
    }

    //  load the user's cart rows, look up each product, and build the ShoppingCart
    public ShoppingCart getByUserId(int userId)
    {
        ShoppingCart cart = new ShoppingCart();
        List<CartItem> cartItems = shoppingCartRepository.findByUserId(userId);
        for(CartItem cartItem : cartItems) {
            Product product = productService.getById(cartItem.getProductId());
            ShoppingCartItem item =new ShoppingCartItem();
            item.setProduct(product);
            item.setQuantity(cartItem.getQuantity());
            cart.add(item);
        }
        return cart;
    }
public ShoppingCart addProduct(int userId,int productId){
        //find product
    Product product = productService.getById(productId);
    CartItem existing = shoppingCartRepository.findByUserIdAndProductId(userId,productId);
    if (existing != null) {
        existing.setQuantity(existing.getQuantity() + 1);
        shoppingCartRepository.save(existing);
        //Checks the product exists
        if(product == null)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    } else
    {CartItem item = new CartItem();
        item.setUserId(userId);
        item.setProductId(productId);
        item.setQuantity(1);
        shoppingCartRepository.save(item);
    }
    return getByUserId(userId);
}

    // add additional methods here
    public ShoppingCart updateQuantity(int userId,int productId,int quantity ){
        CartItem item = shoppingCartRepository.findByUserIdAndProductId(userId,productId);
        if(item != null){
            item.setQuantity(quantity);
            shoppingCartRepository.save(item);
        }
        return getByUserId(userId);
    }
    public void clearCart(int userId){
        shoppingCartRepository.deleteByUserId(userId);
    }
}
