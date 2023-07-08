package com.example.javarushspring2springweb.mapper;

import com.example.javarushspring2springweb.dto.OrderDTO;
import com.example.javarushspring2springweb.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class, ProductMapper.class})
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

//    @Mapping(target = "user", source = "user")
//    OrderDTO orderToOrderDTO(Order order);

    @Mapping(source = "customer", target = "customerDTO", qualifiedByName = "customerToCustomerDTOWithoutPassword")
    OrderDTO orderToOrderDTO(Order order);

    @Mapping(source = "customerDTO", target = "customer", qualifiedByName = "customerDTOToCustomerWithoutPassword")
    Order orderDTOToOrder(OrderDTO orderDTO);
}

