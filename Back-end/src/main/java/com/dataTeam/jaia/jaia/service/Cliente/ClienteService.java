package com.dataTeam.jaia.jaia.service.Cliente;

import com.dataTeam.jaia.jaia.model.Cliente;
import com.dataTeam.jaia.jaia.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private ClienteRepository clienterepo;

    @Transactional
    public Cliente novoCliente(Cliente cliente) {
        return clienterepo.save(cliente);
    }

    public List<Cliente> buscarTodosClientes() {
        return clienterepo.findAll();
    }

    public void excluirClientePorCnpj(String cnpj) {
        Optional<Cliente> clienteOptional = clienterepo.findByCnpj(cnpj);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            clienterepo.delete(cliente);
        } else {
        }
    }

    @Transactional
    public Cliente atualizarClientePorCnpj(String cnpj, Cliente clienteAtualizado) {
        Optional<Cliente> clienteExistente = clienterepo.findByCnpj(cnpj);

        if (clienteExistente.isEmpty()) {
            try {
                throw new Exception("Cliente com CNPJ " + cnpj + " não encontrado.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Cliente cliente = clienteExistente.get();

        cliente.setNome(clienteAtualizado.getNome());
        cliente.setTelefone(clienteAtualizado.getTelefone());
        cliente.setSenha(clienteAtualizado.getSenha());
        cliente.setEndereco(clienteAtualizado.getEndereco());
        cliente.setEmail(clienteAtualizado.getEmail());

        return clienterepo.save(cliente);
    }

    @Transactional
    public Cliente buscarClientePorCnpj(String cnpj) {
        try {
            String cnpjEncoded = URLEncoder.encode(cnpj, StandardCharsets.UTF_8.toString());
            Optional<Cliente> clienteExistente = clienterepo.findByCnpj(cnpjEncoded);
            if (clienteExistente.isEmpty()) {
                try {
                    throw new Exception("Cliente com CNPJ " + cnpj + " não encontrado.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Cliente clienteEncontrado = clienteExistente.get();
            return clienteEncontrado;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

    }

}
