package com.example.usermanagmentservice;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import com.example.usermanagmentservice.exception.NotFoundException;
import com.example.usermanagmentservice.model.User;
import com.example.usermanagmentservice.repository.UserRepository;
import com.example.usermanagmentservice.service.DataValidationService;
import com.example.usermanagmentservice.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private DataValidationService dataValidationService;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userServiceImpl;


    @Test
    public void testGetUsers() {
        // Arrange
        User user1 = new User();
        User user2 = new User();
        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        // Act
        List<User> users = userServiceImpl.getUsers();

        // Assert
        assertNotNull(users);
        assertEquals(2, users.size());
        verify(userRepository, times(1)).findAll();
    }


    @Test
    public void testGetUser_UserFound() {
        // Arrange
        Long userId = 1L;
        User user = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        User foundUser = userServiceImpl.getUser(userId);

        // Assert
        assertNotNull(foundUser);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void testGetUser_UserNotFound() {
        // Arrange
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> userServiceImpl.getUser(userId));
        verify(userRepository, times(1)).findById(userId);
    }


    @Test
    public void testAddUser() {
        // Arrange
        User user = new User();
        user.setPassword("plainPassword");
        String encodedPassword = "encodedPassword";
        when(passwordEncoder.encode("plainPassword")).thenReturn(encodedPassword);
        when(userRepository.save(user)).thenReturn(user);

        // Act
        User savedUser = userServiceImpl.addUser(user);

        // Assert
        assertNotNull(savedUser);
        assertEquals(encodedPassword, user.getPassword());
        verify(passwordEncoder, times(1)).encode("plainPassword");
        verify(userRepository, times(1)).save(user);
    }


    @Test
    public void testUpdateUser_UserFound() {
        // Arrange
        Long userId = 1L;
        User existingUser = new User();
        User updatedUser = new User();
        updatedUser.setPassword("newPassword");
        String encodedPassword = "encodedNewPassword";
        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(passwordEncoder.encode("newPassword")).thenReturn(encodedPassword);
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        // Act
        User result = userServiceImpl.updateUser(userId, updatedUser);

        // Assert
        assertNotNull(result);
        assertEquals(encodedPassword, existingUser.getPassword());
        verify(userRepository, times(1)).findById(userId);
        verify(passwordEncoder, times(1)).encode("newPassword");
        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    public void testUpdateUser_UserNotFound() {
        // Arrange
        Long userId = 1L;
        User updatedUser = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> userServiceImpl.updateUser(userId, updatedUser));
        verify(userRepository, times(1)).findById(userId);
    }


    @Test
    public void testDeleteUser_UserExists() {
        // Arrange
        Long userId = 1L;
        when(userRepository.existsById(userId)).thenReturn(true);

        // Act
        userServiceImpl.deleteUser(userId);

        // Assert
        verify(userRepository, times(1)).existsById(userId);
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    public void testDeleteUser_UserNotFound() {
        // Arrange
        Long userId = 1L;
        when(userRepository.existsById(userId)).thenReturn(false);

        // Act & Assert
        assertThrows(NotFoundException.class, () -> userServiceImpl.deleteUser(userId));
        verify(userRepository, times(1)).existsById(userId);
    }





}