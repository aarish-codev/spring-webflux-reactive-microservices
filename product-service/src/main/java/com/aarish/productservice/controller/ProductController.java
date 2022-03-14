package com.aarish.productservice.controller;


import com.aarish.productservice.dto.ProductDto;
import com.aarish.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/all")
    public Flux<ProductDto> getAll() {
        return this.service.getAllProducts();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ProductDto>> getProduct(@PathVariable String id) {
        return this.service.getProductById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ProductDto> createProduct(@RequestBody Mono<ProductDto> productDtoMono) {
        return this.service.createProduct(productDtoMono);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<ProductDto>> updateProduct(@PathVariable String id, @RequestBody Mono<ProductDto> productDtoMono) {
        return this.service.updateProduct(id, productDtoMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteProduct(@PathVariable String id) {
        return this.service.deleteProduct(id);
    }

    @GetMapping("price-range")
    public Flux<ProductDto> getByPriceRange(@RequestParam("min") int min,
                                            @RequestParam("max") int max) {
        return this.service.getProductsByPriceRange(min, max);
    }
}
