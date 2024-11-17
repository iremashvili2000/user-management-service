package com.example.usermanagmentservice.service;

import com.example.usermanagmentservice.dto.UserDto;
import com.example.usermanagmentservice.exception.Exception;
import com.example.usermanagmentservice.exception.NotFoundException;
import com.example.usermanagmentservice.model.Role;
import com.example.usermanagmentservice.model.User;
import com.example.usermanagmentservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final DataValidationService dataValidationService;

    private final BCryptPasswordEncoder passwordEncoder;


    @Override
    public List<User> getUsers() {
        return  userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("data don't found"));
    }

    @Override
    public User addUser(User userRegistrationDto) {
        String rawPassword = userRegistrationDto.getPassword();
        userRegistrationDto.setPassword(passwordEncoder.encode(rawPassword));
       return userRepository.save(userRegistrationDto);

    }

    @Override
    public User updateUser(Long id, User userRegistrationDto) {
        User entityUser = userRepository.findById(id).orElseThrow(() -> new NotFoundException("data don't found"));
        entityUser.setRole(Role.USER);
        entityUser.setAbout(userRegistrationDto.getAbout());
        entityUser.setGender(userRegistrationDto.getGender());
        entityUser.setAge(userRegistrationDto.getAge());
        entityUser.setUsername(userRegistrationDto.getUsername());
        String rawPassword = userRegistrationDto.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        entityUser.setPassword(encodedPassword);
        entityUser.setEmail(userRegistrationDto.getEmail());
        return userRepository.save(entityUser);
    }

    @Override
    public void deleteUser(Long id) {


        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User not found with ID: " + id);
        }
        userRepository.deleteById(id); // Proceed to delete the user

    }

    @Override
    public User getMe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        User user=userRepository.findByUsername(currentUser.getUsername()).orElseThrow(() -> new NotFoundException("user don't found Invalid username , password or token"));
        return user;
    }
}
