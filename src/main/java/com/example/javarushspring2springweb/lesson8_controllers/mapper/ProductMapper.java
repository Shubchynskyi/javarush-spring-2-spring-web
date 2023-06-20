package com.example.javarushspring2springweb.lesson8_controllers.mapper;

import com.example.javarushspring2springweb.lesson8_controllers.dto.ProductDTO;
import com.example.javarushspring2springweb.lesson8_controllers.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO productToProductDTO(Product product);

    Product productDTOToProduct(ProductDTO productDTO);
}

