package com.aarish.productservice.service;

import com.aarish.productservice.dto.ProductDto;
import com.aarish.productservice.mapper.ProductMapper;
import com.aarish.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;


    public Flux<ProductDto> getAllProducts() {
        return this.repository.findAll()
                .map(ProductMapper.MAPPER::toDto);
    }

    public Mono<ProductDto> getProductById(String id) {
        return this.repository.findById(id)
                .map(ProductMapper.MAPPER::toDto);
    }

    public Mono<ProductDto> createProduct(Mono<ProductDto> productDtoMono) {
        return productDtoMono
                .map(ProductMapper.MAPPER::toEntity)
                .flatMap(this.repository::insert)
                .map(ProductMapper.MAPPER::toDto);
    }

    public Mono<ProductDto> updateProduct(String productId, Mono<ProductDto> productDtoMono) {
        return this.repository.findById(productId)
                .flatMap(p -> productDtoMono
                        .map(ProductMapper.MAPPER::toEntity)
                        .doOnNext(e -> e.setId(productId)))
                .flatMap(this.repository::save)
                .map(ProductMapper.MAPPER::toDto);
    }

    public Mono<Void> deleteProduct(String productId) {
        return this.repository.deleteById(productId);
    }
}
