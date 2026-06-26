package org.yearup.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.yearup.models.Product;
import org.yearup.repository.ProductRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
@Mock
private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;

    @Test
    void search() {
        Product shoes = new Product();
        shoes.setProductId(1);
        shoes.setCategoryId(1);
        shoes.setName("Bottoms");

        Product top = new Product();
        top.setProductId(2);
        top.setCategoryId(2);
        top.setName("Tops");

        when(productRepository.findByCategoryId(1))
                .thenReturn(List.of(shoes));


        List<Product> result =
                productService.search(1,null,null,null);


        assertEquals(1,result.size());
        assertEquals("Flannel Shirt",
                result.get(0).getName());
    }
    }
