package com.aarish.productservice.util;

import com.aarish.productservice.dto.ProductDto;
import com.aarish.productservice.entity.Product;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {

    private EntityDtoUtil() {
    }

    public static ProductDto toDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setDescription(product.getDescription());
        dto.setId(dto.getId());
        dto.setPrice(product.getPrice());
        return dto;
    }

    public static Product toEntity(ProductDto dto) {
        Product product = new Product();
        BeanUtils.copyProperties(dto, product);
        return product;
    }


}
