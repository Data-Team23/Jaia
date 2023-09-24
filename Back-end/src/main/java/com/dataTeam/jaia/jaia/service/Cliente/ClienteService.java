package com.dataTeam.jaia.jaia.service.Cliente;

import com.dataTeam.jaia.jaia.model.Cliente;
import com.dataTeam.jaia.jaia.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Cliente buscarPorId(Long id) {
        Optional<Cliente> clienteOp = clienterepo.findById(id);
        if(clienteOp.isEmpty()) {
            throw new IllegalArgumentException("Usuario nao encontrado!");
        }
        return clienteOp.get();
    }


}
