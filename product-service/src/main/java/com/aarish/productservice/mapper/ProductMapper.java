package com.aarish.productservice.mapper;


import com.aarish.productservice.dto.ProductDto;
import com.aarish.productservice.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

//        @Mapping(source = "product" , target = "productDto")
    ProductDto toDto(Product product);

    Product toEntity(ProductDto dto);
}
