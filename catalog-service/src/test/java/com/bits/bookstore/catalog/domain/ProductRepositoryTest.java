package com.bits.bookstore.catalog.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest(
        properties = {
            "spring.test.database.replace=none",
            "spring.datasource.url=jdbc:tc:postgresql:16-alpine:///db",
        })
// @Import(ContainersConfig.class)
@Sql("/test-data.sql")
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    // You don't need to test the methods provided by Spring Data JPA.
    // This test is to demonstrate how to write tests for the repository layer.
    @Test
    void shouldGetProducts() {
        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(15);
    }

    @Test
    public void shouldReturnProductByCode() {
        Product product = productRepository.findByCode("P111").orElseThrow();
        assertThat(product.getCode().equals("P111"));
        assertThat(
                product.getName()
                        .equals(
                                "Here is the first volume in George R. R. Martin’s magnificent cycle of novels that includes A Clash of Kings and A Storm of Swords."));
        assertThat(product.getDescription().equals("A Game of Thrones"));
        assertThat(product.getImage_url().equals("https://images.gr-assets.com/books/1436732693l/13496.jpg"));
        assertThat(product.getPrice()).isEqualTo(32.0);
    }

    @Test
    void shouldReturnEmptyWhenProductCodeNotExists() {
        assertThat(productRepository.findByCode("invalid_product_code")).isEmpty();
    }
}
