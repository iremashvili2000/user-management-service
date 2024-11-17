package com.example.usermanagmentservice.service;

import com.example.usermanagmentservice.dto.UserDto;
import com.example.usermanagmentservice.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User getUser(Long idString);

    User addUser(User userRegistrationDto);

    User updateUser(Long id, User userRegistrationDto);

    void deleteUser(Long id);

    User getMe();
}
