package org.yearup.controllers;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.yearup.models.CartItem;
import org.yearup.repository.ShoppingCartRepository;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@Sql(
        scripts = "classpath:test-insert-data.sql",
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
class ShoppingCartControllerTest {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Test
    void addToCart() {
            // arrange
            CartItem item = new CartItem();
            item.setUserId(1);
            item.setProductId(20);
            item.setQuantity(1);

        // act

        CartItem saved = shoppingCartRepository.save(item);

        // assert
            assertTrue(saved.getCartItemId() > 0);
            assertEquals(20, saved.getProductId());
            assertEquals(1, saved.getQuantity());
        }
    }
