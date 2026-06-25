package org.yearup.controllers;

import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.Test;
import org.yearup.models.Category;


class CategoriesControllerTest {

    @Test
    void addCategory() {


        Category category = new Category();
        category.setName("Shoes");


        Category savedCategory = new Category();
        savedCategory.setCategoryId(3);
        savedCategory.setName("Shoes");


    }
}