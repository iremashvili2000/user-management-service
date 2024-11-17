package com.example.usermanagmentservice.controller.implementation;


import com.example.usermanagmentservice.controller.AdminControllerInterface;
import com.example.usermanagmentservice.dto.UserDto;
import com.example.usermanagmentservice.exception.NotFoundException;
import com.example.usermanagmentservice.mapper.UsersMapper;
import com.example.usermanagmentservice.model.User;
import com.example.usermanagmentservice.repository.UserRepository;
import com.example.usermanagmentservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AdminController implements AdminControllerInterface {

    private final UserService userService;

    private final UsersMapper usersMapper;



    @Override
    public ResponseEntity<List<UserDto>> getUsers(){
        return ResponseEntity.ok(usersMapper.usersToUserDtos(userService.getUsers()));
    }

    public User getMe(){
        return userService.getMe();
    }


    @Override
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        log.info("starting get user's information with id {}",id);
        return ResponseEntity.ok(usersMapper.userToUserDto(userService.getUser(id)));
    }

    @Override
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto){
        log.info("starting add user");
        return ResponseEntity.ok(usersMapper.userToUserDto(userService.addUser(usersMapper.userDtoToUser(userDto))));
    }

    @Override
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Long id){
        log.info("starting update user with id {}",id);
        return ResponseEntity.ok(usersMapper.userToUserDto(userService.updateUser(id,usersMapper.userDtoToUser(userDto))));
    }

    @Override
    public void deleteUser(@PathVariable Long id){
        log.info("starting delete user with id {}",id);
        userService.deleteUser(id);
    }





}
