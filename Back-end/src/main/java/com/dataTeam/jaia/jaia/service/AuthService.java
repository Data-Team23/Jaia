package com.dataTeam.jaia.jaia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataTeam.jaia.jaia.model.Administrador;
import com.dataTeam.jaia.jaia.model.AuthResponse;
import com.dataTeam.jaia.jaia.model.Cliente;
import com.dataTeam.jaia.jaia.model.Funcionario;
import com.dataTeam.jaia.jaia.repository.AdministradorRepository;
import com.dataTeam.jaia.jaia.repository.ClienteRepository;
import com.dataTeam.jaia.jaia.repository.FuncionarioRepository;

@Service
public class AuthService {

    private final ClienteRepository clienteRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final AdministradorRepository administradorRepository;

    @Autowired
    public AuthService(ClienteRepository clienteRepository, FuncionarioRepository funcionarioRepository, AdministradorRepository administradorRepository) {
        this.clienteRepository = clienteRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.administradorRepository = administradorRepository;
    }

    public AuthResponse authenticateCliente(String cnpj, String senha) {
        Cliente cliente = clienteRepository.findByCnpjAndSenha(cnpj, senha);
        if (cliente != null) {
            return new AuthResponse("Cliente autenticado com sucesso!", cliente.getId());
        } else {
            return new AuthResponse("Credenciais inválidas para cliente", null);
        }
    }

    public AuthResponse authenticateFuncionario(String cpf, String senha) {
        Funcionario funcionario = funcionarioRepository.findByCpfAndSenha(cpf, senha);
        if (funcionario != null) {
            return new AuthResponse("Funcionário autenticado com sucesso!", funcionario.getId());
        } else {
            return new AuthResponse("Credenciais inválidas para funcionário", null);
        }
    }

    public AuthResponse authenticateAdministrador(String usuario, String senha) {
        Administrador administrador = administradorRepository.findByUsuarioAndSenha(usuario, senha);
        if (administrador != null) {
            return new AuthResponse("Administrador autenticado com sucesso!", administrador.getId());
        } else {
            return new AuthResponse("Credenciais inválidas para administrador", null);
        }
    }
}

