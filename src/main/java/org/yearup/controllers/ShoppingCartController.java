package org.yearup.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.models.User;
import org.yearup.service.ShoppingCartService;
import org.yearup.service.UserService;

import java.security.Principal;
@RestController
@RequestMapping("/cart")
@PreAuthorize("isAuthenticated()")
@CrossOrigin
// convert this class to a REST controller
// only logged in users should have access to these actions
public class ShoppingCartController
{
    // a shopping cart controller depends on the service layer
    private ShoppingCartService shoppingCartService;
    private UserService userService;
public ShoppingCartController(ShoppingCartService shoppingCartService, UserService userService){
    this.shoppingCartService = shoppingCartService;
    this.userService = userService;
}



    // each method in this controller requires a Principal object as a parameter
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ShoppingCart getCart(Principal principal) {

        // get the currently logged in username
        String userName = principal.getName();
        // find database user by username
        User user = userService.getByUserName(userName);
        int userId = user.getId();

        // use the shoppingCartService to get all items in the cart and return the cart
        return shoppingCartService.getByUserId(user.getId());
    }
// add a POST method to add a product to the cart - the url should be
    // https://localhost:8080/cart/products/15  (15 is the productId to be added)
    // return the updated cart with status 201 Created

      @PostMapping("/products/{productId}")
      @PreAuthorize ("isAuthenticated()")
      @ResponseStatus(HttpStatus.CREATED)
      public ShoppingCart addProduct(@PathVariable int productId,Principal principal)
      {
          System.out.println("Principal="+principal);
          String userName = principal.getName();
          User user = userService.getByUserName(userName);
          int userId = user.getId();
          shoppingCartService.addProduct(user.getId(),productId);
          return  shoppingCartService.getByUserId(user.getId());
      }
    @PostMapping("/cart/products")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> addToCart(@PathVariable int productId,
                                       Principal principal)
    {
        User user = userService.getByUserName(principal.getName());
        shoppingCartService.addProduct(user.getId(),productId);
        return ResponseEntity.ok(shoppingCartService.getByUserId(user.getId()));
    }

    // add a PUT method to update an existing product in the cart - the url should be

    // the BODY should be a ShoppingCartItem - quantity is the only value that will be updated; return the cart (200 OK)
@PutMapping("/products/{productId}")
    public ShoppingCart updateProduct(@PathVariable int productId, @RequestBody ShoppingCartItem item, Principal principal )
    {
        String userName = principal.getName();
        User user = userService.getByUserName(userName);
        int userId = user.getId();
        shoppingCartService.updateQuantity(userId,productId,item.getQuantity()
        );
        return shoppingCartService.getByUserId(userId);
    }

    // add a DELETE method to clear all products from the current users cart

    @DeleteMapping
    @PreAuthorize("isAuthenticated()")
    public ShoppingCart clearCart( Principal principal)
    {
        User user = userService.getByUserName(principal.getName());
        shoppingCartService.clearCart(user.getId()
        );

        return shoppingCartService.getByUserId(user.getId());
    }
    //This Deletes products by Id
    @DeleteMapping("/products/{productId}")
    @PreAuthorize("isAuthenticated()")
    public ShoppingCart deleteProduct(@PathVariable int productId, Principal principal)
    {
        User user = userService.getByUserName(principal.getName());
        shoppingCartService.deleteProduct(
                user.getId(),
                productId
        );

        return shoppingCartService.getByUserId(user.getId());
    }
}
