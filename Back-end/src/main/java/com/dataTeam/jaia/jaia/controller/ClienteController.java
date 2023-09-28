package com.dataTeam.jaia.jaia.controller;


import com.dataTeam.jaia.jaia.model.Cliente;
import com.dataTeam.jaia.jaia.service.Cliente.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/cliente")
@CrossOrigin(origins = "http://localhost:5173")
public class ClienteController {

    @Autowired
    private IClienteService service;

    @GetMapping
    public List<Cliente> buscartodos(){
        return service.buscarTodosClientes();
    }

    @PostMapping
    public Cliente novoCliente(@RequestBody Cliente cliente){
        return service.novoCliente(cliente);
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<?> buscarClientePorCnpj(@PathVariable String cnpj) {
        try {
            Cliente cliente = service.buscarClientePorCnpj(cnpj);
                if (cliente != null) {
                    return ResponseEntity.ok(cliente);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
                }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao buscar o cliente");
        }
    }

    @DeleteMapping("/excluir/{cnpj}")
    public ResponseEntity<String> excluirClientePorCnpj(@PathVariable String cnpj) {
        try {
            service.excluirClientePorCnpj(cnpj);
            return ResponseEntity.ok("Cliente excluído com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao excluir o cliente");
        }
    }

    @PutMapping("/atualizar/{cnpj}")
    public ResponseEntity<?> atualizarClientePorCnpj(@PathVariable String cnpj, @RequestBody Cliente clienteAtualizado) {
        try {
            Cliente clienteAtualizadoResult = service.atualizarClientePorCnpj(cnpj, clienteAtualizado);
            return ResponseEntity.ok(clienteAtualizadoResult); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao atualizar o cliente");
        }
    }

}
