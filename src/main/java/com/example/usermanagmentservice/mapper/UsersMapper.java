package com.example.usermanagmentservice.mapper;

import com.example.usermanagmentservice.dto.UserDto;
import com.example.usermanagmentservice.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsersMapper {



    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email",target = "email")
    @Mapping(source = "age", target = "age")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "about", target = "about")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "password", target = "password")
    User userDtoToUser(UserDto userDto);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email",target = "email")
    @Mapping(source = "age", target = "age")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "about", target = "about")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "password", target = "password")
    UserDto userToUserDto(User user);

    List<User>  userDtosToUsers(List<UserDto> userDtoList);


    List<UserDto>  usersToUserDtos(List<User> userDtoList);

}
