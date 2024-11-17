package com.example.usermanagmentservice.service;


import com.example.usermanagmentservice.exception.InvalidDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DataValidationService {


    public Long parseStringToLong(String idString) {

        Long id;
        log.info("Fetching data with ID: {}", idString);
        try {
            id = Long.parseLong(idString); // Parse the user ID
            log.info("Successfully parsed data ID: {}", id); // Log successful parsing
        } catch (NumberFormatException e) {
            log.error("Invalid data ID format: {}", idString, e); // Log the error with exception details
            throw new InvalidDataException("Invalid data ID format: " + idString);
        }

        return id;

    }


}
