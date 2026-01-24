package com.bits.bookstore.catalog.domain;

public record ProductDto(String code, String name, String description, String imageUrl, double price) {}
