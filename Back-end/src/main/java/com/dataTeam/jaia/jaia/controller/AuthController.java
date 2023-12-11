package com.dataTeam.jaia.jaia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dataTeam.jaia.jaia.model.AuthRequest;
import com.dataTeam.jaia.jaia.model.AuthResponse;
import com.dataTeam.jaia.jaia.service.AuthService;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        String username = authRequest.getUsername();
        String password = authRequest.getPassword();
        String tipoDocumento = authRequest.getTipoDocumento();

        AuthResponse result = null;

        if ("cnpj".equals(tipoDocumento)) {
            result = authService.authenticateCliente(username, password);
        } else if ("cpf".equals(tipoDocumento)) {
            result = authService.authenticateFuncionario(username, password);
        }  else if ("admin".equals(tipoDocumento)) {
            result = authService.authenticateAdministrador(username, password);
        }

        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthResponse("Tipo de documento inválido", null));
        }
    }

}
