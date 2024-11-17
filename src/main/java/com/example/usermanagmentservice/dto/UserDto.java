package com.example.usermanagmentservice.dto;

import com.example.usermanagmentservice.model.Role;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UserDto(

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)  Long id,

         String username,

         String email,

         Integer age,

         String gender,

         String about,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY) Role role,

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) String password
) {
}
