package com.example.usermanagmentservice.service;


import com.example.usermanagmentservice.dto.AuthanticationDto;
import com.example.usermanagmentservice.dto.UserDto;
import com.example.usermanagmentservice.exception.InvalidDataException;
import com.example.usermanagmentservice.mapper.UsersMapper;
import com.example.usermanagmentservice.model.Role;
import com.example.usermanagmentservice.model.User;
import com.example.usermanagmentservice.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final UsersMapper usersMapper;

    public AuthenticationService(
            UserRepository userRepository,
            BCryptPasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,
            UsersMapper usersMapper) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.usersMapper = usersMapper;
    }

    public User signup(UserDto input) {
        var user = usersMapper.userDtoToUser(input);
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(userRepository.findByUsername(input.username()).isPresent()){
            throw new InvalidDataException("this username already exists");
        }
        return userRepository.save(user);
    }

    public User authenticate(AuthanticationDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.username(),
                        input.password()
                )
        );

        return userRepository.findByUsername(input.username()).get();
    }



}
