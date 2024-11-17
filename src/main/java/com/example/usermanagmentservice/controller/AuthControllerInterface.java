package com.example.usermanagmentservice.controller;


import com.example.usermanagmentservice.dto.AuthanticationDto;
import com.example.usermanagmentservice.dto.LoginResponse;
import com.example.usermanagmentservice.dto.UserDto;
import com.example.usermanagmentservice.model.Token;
import com.example.usermanagmentservice.model.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "api", description = "API for login")
@RequestMapping("/api/public")
public interface AuthControllerInterface {

    @PostMapping("/signup")
    public ResponseEntity<UserDto> register(@RequestBody UserDto registerUserDto);


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody AuthanticationDto loginUserDto);




}
