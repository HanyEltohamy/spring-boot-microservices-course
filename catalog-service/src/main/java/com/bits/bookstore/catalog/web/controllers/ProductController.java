package com.bits.bookstore.catalog.web.controllers;

import com.bits.bookstore.catalog.domain.PageResult;
import com.bits.bookstore.catalog.domain.ProductDto;
import com.bits.bookstore.catalog.domain.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/products")
class ProductController {

    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    PageResult<ProductDto> getProductPage(@RequestParam(name = "page", defaultValue = "1") int pageNum) {
        return productService.getProductPageByPageNumber(pageNum);
    }

    @GetMapping("/{code}")
    ResponseEntity<ProductDto> getProductByCode(@PathVariable String code) {
        return ResponseEntity.ok(productService.getProductByCode(code));
    }
}
