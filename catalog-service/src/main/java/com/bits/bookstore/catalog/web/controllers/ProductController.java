package com.bits.bookstore.catalog.web.controllers;

import com.bits.bookstore.catalog.domain.PageResult;
import com.bits.bookstore.catalog.domain.ProductDto;
import com.bits.bookstore.catalog.domain.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/products")
class ProductController {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    PageResult<ProductDto> getProductPage(@RequestParam(name = "page", defaultValue = "1") int pageNum) {
        log.info("Get Product Page Number {}", pageNum);
        return productService.getProductPageByPageNumber(pageNum);
    }

    @GetMapping("/{code}")
    ResponseEntity<ProductDto> getProductByCode(@PathVariable String code) {
        return ResponseEntity.ok(productService.getProductByCode(code));
    }
}
