package com.dataTeam.jaia.jaia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataTeam.jaia.jaia.model.Cliente;
import com.dataTeam.jaia.jaia.model.Funcionario;
import com.dataTeam.jaia.jaia.repository.ClienteRepository;
import com.dataTeam.jaia.jaia.repository.FuncionarioRepository;

@Service
public class AuthService {

    private final ClienteRepository clienteRepository;
    private final FuncionarioRepository funcionarioRepository;

    @Autowired
    public AuthService(ClienteRepository clienteRepository, FuncionarioRepository funcionarioRepository) {
        this.clienteRepository = clienteRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    public String authenticateCliente(String cnpj, String senha) {
        Cliente cliente = clienteRepository.findByCnpjAndSenha(cnpj, senha);
        if (cliente != null) {
            return "Cliente autenticado com sucesso!";
        } else {
            return "Credenciais inválidas para cliente";
        }
    }

    public String authenticateFuncionario(String cpf, String senha) {
        Funcionario funcionario = funcionarioRepository.findByCpfAndSenha(cpf, senha);
        if (funcionario != null) {
            return "Funcionário autenticado com sucesso!";
        } else {
            return "Credenciais inválidas para funcionário";
        }
    }
}

