package com.bits.bookstore.catalog.domain;

import com.bits.bookstore.catalog.ApplicationProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ApplicationProperties applicationProperties;

    public ProductService(ProductRepository productRepository, ApplicationProperties applicationProperties) {
        this.productRepository = productRepository;
        this.applicationProperties = applicationProperties;
    }

    public PageResult<ProductDto> getProductPageByPageNumber(int pageNum) {
        pageNum = pageNum <= 1 ? 0 : pageNum - 1;
        Sort sort = Sort.by("name").ascending();
        Pageable pageable = PageRequest.of(pageNum, applicationProperties.pageSize(), sort);
        Page<ProductDto> page = productRepository.findAll(pageable).map(ProductMapper::toProductDto);
        return new PageResult<>(
                page.getContent(),
                page.getTotalElements(),
                page.getNumber() + 1,
                page.getTotalPages(),
                page.isFirst(),
                page.isLast(),
                page.hasNext(),
                page.hasPrevious());
    }

    public ProductDto getProductByCode(String code) {
        return productRepository
                .findByCode(code)
                .map(ProductMapper::toProductDto)
                .orElseThrow(() -> ProductNotFoundException.forCode(code));
    }
}
