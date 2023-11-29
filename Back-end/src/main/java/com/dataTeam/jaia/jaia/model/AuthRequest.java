package com.dataTeam.jaia.jaia.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {

    private String username;
    private String password;
    private String tipoDocumento;

    public AuthRequest() {}

    public AuthRequest(String username, String password, String tipoDocumento) {
        this.username = username;
        this.password = password;
        this.tipoDocumento = tipoDocumento;
    }

}
