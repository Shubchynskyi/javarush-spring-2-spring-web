package com.example.javarushspring2springweb.controller.rest;

import com.example.javarushspring2springweb.entity.Product;
import com.example.javarushspring2springweb.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@WebMvcTest(ProductsRestController.class)
class ProductsRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void shouldReturnAllProducts() throws Exception {
        List<Product> products = Arrays.asList(
                new Product(1L, "Product1", "Description1"),
                new Product(2L, "Product2", "Description2")
        );

        when(productService.getAll()).thenReturn(products);

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("Product1")))
                .andExpect(jsonPath("$[1].title", is("Product2")));
    }

    @Test
    void shouldCreateProduct() throws Exception {
        Product product = new Product(1L, "Product1", "Description1");
        when(productService.create(anyString(), anyString())).thenReturn(Optional.of(product));

        mockMvc.perform(post("/api/products")
                        .param("title", "Product1")
                        .param("description", "Description1"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is("Product1")));
    }

    @Test
    void shouldReturnBadRequestWhenCreatingProductFails() throws Exception {
        when(productService.create(anyString(), anyString())).thenReturn(Optional.empty());

        mockMvc.perform(post("/api/products")
                        .param("title", "InvalidProduct")
                        .param("description", "InvalidDescription"))
                .andExpect(status().isBadRequest());
    }
}
