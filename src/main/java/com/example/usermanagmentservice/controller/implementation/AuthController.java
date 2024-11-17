package com.example.usermanagmentservice.controller.implementation;


import com.example.usermanagmentservice.controller.AuthControllerInterface;
import com.example.usermanagmentservice.dto.AuthanticationDto;
import com.example.usermanagmentservice.dto.LoginResponse;
import com.example.usermanagmentservice.dto.UserDto;
import com.example.usermanagmentservice.mapper.UsersMapper;
import com.example.usermanagmentservice.model.User;
import com.example.usermanagmentservice.service.AuthenticationService;
import com.example.usermanagmentservice.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthController implements AuthControllerInterface {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    private final UsersMapper usersMapper;




    public ResponseEntity<UserDto> register(@RequestBody UserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(usersMapper.userToUserDto(registeredUser));
    }


    public ResponseEntity<LoginResponse> authenticate(@RequestBody AuthanticationDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }



}
