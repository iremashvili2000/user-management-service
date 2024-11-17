package com.example.usermanagmentservice.controller;


import com.example.usermanagmentservice.dto.UserDto;
import com.example.usermanagmentservice.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Admins", description = "API for managing admins")
@RequestMapping("/api/admins")
public interface AdminControllerInterface {


    @RequestMapping(value = "/me",method = RequestMethod.POST)
    public User getMe();
    @Operation(summary = "Get all users", description = "Returns a list of all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user list",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class)))
    })
    @GetMapping
    ResponseEntity<List<UserDto>> getUsers();

    @Operation(summary = "Get a user by ID", description = "Returns a user by their unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("{id}")
    ResponseEntity<UserDto> getUser(
                                    @Parameter(description = "ID of the user to retrieve") @PathVariable Long id);

    @Operation(summary = "Add a new user", description = "Creates a new user in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    ResponseEntity<UserDto> addUser(
            @Parameter(description = "User details for the new user") @RequestBody UserDto userDto);

    @Operation(summary = "Delete a user by ID", description = "Deletes a user by their unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("{id}")
    void deleteUser(
            @Parameter(description = "ID of the user to delete") @PathVariable Long id);

    @Operation(summary = "Update a user", description = "Updates an existing user by their unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("{id}")
    ResponseEntity<UserDto> updateUser(
            @Parameter(description = "Updated user details") @RequestBody UserDto userDto,
            @Parameter(description = "ID of the user to update") @PathVariable Long id);


}
