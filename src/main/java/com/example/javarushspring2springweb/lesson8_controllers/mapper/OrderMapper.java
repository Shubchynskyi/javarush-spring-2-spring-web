package com.example.javarushspring2springweb.lesson8_controllers.mapper;

import com.example.javarushspring2springweb.lesson8_controllers.dto.OrderDTO;
import com.example.javarushspring2springweb.lesson8_controllers.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UserMapper.class, ProductMapper.class})
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

//    @Mapping(target = "user", source = "user")
//    OrderDTO orderToOrderDTO(Order order);

    @Mapping(source = "user", target = "userDTO", qualifiedByName = "userToUserDTOWithoutPassword")
    OrderDTO orderToOrderDTO(Order order);

    @Mapping(source = "userDTO", target = "user", qualifiedByName = "userDTOToUserWithoutPassword")
    Order orderDTOToOrder(OrderDTO orderDTO);
}

