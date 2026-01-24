package com.bits.bookstore.catalog.domain;

class ProductMapper {
    static ProductDto toProductDto(Product product) {
        return new ProductDto(
                product.getCode(),
                product.getName(),
                product.getDescription(),
                product.getImage_url(),
                product.getPrice());
    }
}
