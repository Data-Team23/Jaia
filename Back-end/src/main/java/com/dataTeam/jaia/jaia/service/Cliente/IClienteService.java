package com.dataTeam.jaia.jaia.service.Cliente;

import com.dataTeam.jaia.jaia.model.Cliente;

import java.util.List;

public interface IClienteService {

    public Cliente novoCliente (Cliente cliente );

    public List<Cliente> buscarTodosClientes();

    public Cliente buscarPorId(Long Id);

}
