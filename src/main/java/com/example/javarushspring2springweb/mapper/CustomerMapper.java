package com.example.javarushspring2springweb.mapper;

import com.example.javarushspring2springweb.dto.CustomerDTO;
import com.example.javarushspring2springweb.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Named("customerToCustomerDTOWithoutPassword")
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "password", ignore = true)
    })
    CustomerDTO customerToCustomerDTOWithoutPassword(Customer customer);

    @Named("customerDTOToCustomerWithoutPassword")
    @Mappings({
//            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name")
//            @Mapping(target = "password", ignore = true)
    })
    Customer customerDTOToCustomerWithoutPassword(CustomerDTO customerDTO);

    CustomerDTO customerToCustomerDTO(Customer customer);

//    User userDTOToUser(UserDTO userDto);
}
