package com.example.javarushspring2springweb.lesson8_controllers.mapper;

import com.example.javarushspring2springweb.lesson8_controllers.dto.UserDTO;
import com.example.javarushspring2springweb.lesson8_controllers.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Named("userToUserDTOWithoutPassword")
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "password", ignore = true)
    })
    UserDTO userToUserDTOWithoutPassword(User user);

//    @Named("userDTOToUserWithoutPassword")
//    @Mappings({
////            @Mapping(target = "id", source = "id"),
//            @Mapping(target = "name", source = "name")
////            @Mapping(target = "password", ignore = true)
//    })
//    User userDTOToUserWithoutPassword(UserDTO userDTO);

    UserDTO userToUserDTO(User user);

//    User userDTOToUser(UserDTO userDto);
}
