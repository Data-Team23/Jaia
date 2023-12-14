package com.dataTeam.jaia.jaia.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    private String message;
    private Long id;

    public AuthResponse(String message, Long id) {
        this.message = message;
        this.id = id;
    }
}
