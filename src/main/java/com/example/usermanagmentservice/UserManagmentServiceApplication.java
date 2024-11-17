package com.example.usermanagmentservice;

import com.example.usermanagmentservice.model.Role;
import com.example.usermanagmentservice.model.User;
import com.example.usermanagmentservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@ConfigurationPropertiesScan
public class UserManagmentServiceApplication {

   



    public static void main(String[] args) {

        SpringApplication.run(UserManagmentServiceApplication.class, args);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("hashedpassword2");
        System.out.println("password in database " +hashedPassword);

    }

}
