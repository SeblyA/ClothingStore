package org.yearup.service;

import org.springframework.stereotype.Service;
import org.yearup.models.CartItem;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.repository.ShoppingCartRepository;

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

    public ShoppingCart getByUserId(int userId)
    {
        // load the user's cart rows, look up each product, and build the ShoppingCart
        return null;
    }
public ShoppingCart addProduct(int userId,int productId){
        //find product
    Product product = productService.getById(productId);
    CartItem existing = shoppingCartRepository.findByUserIdAndProductId(userId,productId);
    if (existing != null) {
        existing.setQuantity(existing.getQuantity() + 1);
        shoppingCartRepository.save(existing);
    } else
    {CartItem item = new CartItem();
        item.setUserId(userId);
        item.setProductId(productId);
        item.setQuantity(1);
        shoppingCartRepository.save(item);
    }
    return getByUserId(userId);
}
public void clearCart(int userId){
        shoppingCartRepository.deleteByUserId(userId);
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
}
