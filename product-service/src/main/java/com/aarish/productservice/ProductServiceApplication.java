package com.aarish.productservice;

import com.aarish.productservice.dto.ProductDto;
import com.aarish.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class ProductServiceApplication implements CommandLineRunner {

    @Autowired
    private ProductService service;

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        ProductDto p1 = new ProductDto("4k-tv", 1000);
        ProductDto p2 = new ProductDto("slr-camera", 750);
        ProductDto p3 = new ProductDto("iphone", 800);
        ProductDto p4 = new ProductDto("headphone", 100);

        Flux.just(p1, p2, p3, p4)
                //.concatWith(newProducts())
                .flatMap(p -> this.service.createProduct(Mono.just(p)))
                .subscribe(System.out::println);
    }

    private Flux<ProductDto> newProducts() {
        return Flux.range(1, 1000)
                .delayElements(Duration.ofSeconds(2))
                .map(i -> new ProductDto("product-" + i, ThreadLocalRandom.current().nextInt(10, 100)));
    }
}
