package com.example.usermanagmentservice.controller.implementation;

import com.example.usermanagmentservice.controller.UserControllerInterface;
import com.example.usermanagmentservice.dto.UserDto;
import com.example.usermanagmentservice.exception.NotFoundException;
import com.example.usermanagmentservice.mapper.UsersMapper;
import com.example.usermanagmentservice.model.User;
import com.example.usermanagmentservice.repository.UserRepository;
import com.example.usermanagmentservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Slf4j
public class UserController implements UserControllerInterface {

    private final UserService userService;

    private final UsersMapper usersMapper;

    private final UserRepository userRepository;



    @Override
    public ResponseEntity<List<UserDto>> getUsers(){
        return ResponseEntity.ok(usersMapper.usersToUserDtos(userService.getUsers()));
    }


    public ResponseEntity<UserDto> getMe(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        User user=userRepository.findByUsername(currentUser.getUsername()).orElseThrow(() -> new NotFoundException("user don't found Invalid username , password or token"));
        return ResponseEntity.ok(usersMapper.userToUserDto(user));
    }


    @Override
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        return ResponseEntity.ok(usersMapper.userToUserDto(userService.getUser(id)));
    }



    @Override
    public ResponseEntity<UserDto> updateMe(@RequestBody UserDto userDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        User user=userRepository.findByUsername(currentUser.getUsername()).orElseThrow(() -> new NotFoundException("user don't found Invalid username , password or token"));
        return ResponseEntity.ok(usersMapper.userToUserDto(userService.updateUser(userDto.id(), user)));
    }

    @Override
    public void deleteUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        User user=userRepository.findByUsername(currentUser.getUsername()).orElseThrow(() -> new NotFoundException("user don't found Invalid username , password or token"));
        userService.deleteUser(user.getId());
    }

}
