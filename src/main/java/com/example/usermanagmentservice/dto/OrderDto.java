package com.example.usermanagmentservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderDto(


        @JsonProperty(access = JsonProperty.Access.READ_ONLY) Long id,

        Long userId,

        Integer quantity,

        Double price,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY) Status status,



        @JsonProperty(access = JsonProperty.Access.READ_ONLY) String created,


        String product
) {
}