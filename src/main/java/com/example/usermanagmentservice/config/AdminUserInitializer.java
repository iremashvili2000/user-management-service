package com.example.usermanagmentservice.config;

import com.example.usermanagmentservice.model.Role;
import com.example.usermanagmentservice.model.User;
import com.example.usermanagmentservice.model.enums.Gender;
import com.example.usermanagmentservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class AdminUserInitializer implements CommandLineRunner {

    private final UserRepository userRepository;  // Your user repository to save the user
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminUserInitializer(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check if the admin user already exists to avoid duplicate creation
        if (!userRepository.findByUsername("admin").isPresent()) {
            // Create a new admin user
            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setPassword(passwordEncoder.encode("admin"));  // You can set a default password
            adminUser.setRole(Role.ADMIN);
            adminUser.setGender(Gender.MALE);
            adminUser.setEmail("admin@gmail.com");
            adminUser.setAge(25);

            // Save the user to the database
            userRepository.save(adminUser);
            System.out.println("Admin user created successfully!");
        } else {
            System.out.println("Admin user already exists.");
        }
    }
}