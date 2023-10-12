package com.dataTeam.jaia.jaia.service.Cliente;

import com.dataTeam.jaia.jaia.model.Cliente;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteService {

    public Cliente novoCliente(Cliente cliente);

    public Cliente buscarClientePorCnpj(Integer cnpj);

    public List<Cliente> buscarTodosClientes();

    public void excluirClientePorCnpj(Integer cnpj);

    Cliente atualizarClientePorCnpj(Integer cnpj, Cliente clienteAtualizado);

}
