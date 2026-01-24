package com.bits.bookstore.catalog.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_generator")
    @SequenceGenerator(name = "product_id_generator", sequenceName = "product_id_seq")
    private Long id;
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Product code is required")
    private String code;
    @Column(nullable = false)
    @NotBlank(message = "Product name is required")
    private String name;
    private String description;
    private String image_url;
    @Column(nullable = false)
    @DecimalMin("0.1")
    @NotNull(message = "Product price is required")
    private BigDecimal price;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ProductEntity() {
    }

    public ProductEntity(String code, String name, String description, String image_url, BigDecimal price) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.image_url = image_url;
        this.price = price;
    }
}
