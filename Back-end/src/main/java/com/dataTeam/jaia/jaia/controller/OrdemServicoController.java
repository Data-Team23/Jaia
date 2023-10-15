package com.dataTeam.jaia.jaia.controller;

import com.dataTeam.jaia.jaia.DTO.OrdemServicoDTO;
import com.dataTeam.jaia.jaia.model.Checklist;
import com.dataTeam.jaia.jaia.model.Cliente;
import com.dataTeam.jaia.jaia.model.Funcionario;
import com.dataTeam.jaia.jaia.model.OrdemServico;
import com.dataTeam.jaia.jaia.repository.ChecklistRepository;
import com.dataTeam.jaia.jaia.repository.ClienteRepository;
import com.dataTeam.jaia.jaia.repository.FuncionarioRepository;
import com.dataTeam.jaia.jaia.repository.OrdemServicoRepository;
import com.dataTeam.jaia.jaia.service.OrdemServico.IOrdemServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ordem-servico")
@CrossOrigin
public class OrdemServicoController {

    @Autowired
    private IOrdemServicoService service;

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private ChecklistRepository checklistRepository;

    @GetMapping
    public List<OrdemServico> buscartodos() {
        return service.buscarTodasOrdemServico();
    }

    @GetMapping("/{id}")
    public OrdemServico buscarOrdemServicoPorId(@PathVariable Long id) {
        return ordemServicoRepository.findById(id).orElse(null);
    }

    @PostMapping("/criar")
    public OrdemServico criarOrdemServico(@RequestBody OrdemServicoDTO ordemServicoDTO) {
        Funcionario supervisor = funcionarioRepository.findById(ordemServicoDTO.getIdSupervisor())
                .orElseThrow(() -> new RuntimeException("Supervisor não encontrado"));

        Cliente cliente = clienteRepository.findById(ordemServicoDTO.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Checklist checklist = checklistRepository.findById(ordemServicoDTO.getIdChecklist())
                .orElseThrow(() -> new RuntimeException("Checklist não encontrado"));

        OrdemServico ordemServico = new OrdemServico();
        ordemServico.setNome_ordem(ordemServicoDTO.getNome_ordem());
        ordemServico.setTipo_inspecao(ordemServicoDTO.getTipo_inspecao());
        ordemServico.setStatus_ordem(ordemServicoDTO.getStatus_ordem());
        ordemServico.setId_supervisor(supervisor);
        ordemServico.setId_cli(cliente);
        ordemServico.setId_check(checklist);

        return ordemServicoRepository.save(ordemServico);
    }

    @DeleteMapping("/{id}")
    public String excluirOrdemServico(@PathVariable Long id) {
        if (ordemServicoRepository.existsById(id)) {
            ordemServicoRepository.deleteById(id);
            return "Ordem de Serviço com ID: " + id + " excluída com sucesso.";
        } else {
            return "Ordem de Serviço com ID: " + id + " não encontrada.";
        }
    }

    @PutMapping("/{id}")
    public String atualizarOrdemServico(@PathVariable Long id, @RequestBody OrdemServicoDTO ordemServicoDTO) {
        if (ordemServicoRepository.existsById(id)) {
            OrdemServico ordemServico = ordemServicoRepository.getOne(id);
            ordemServico.setNome_ordem(ordemServicoDTO.getNome_ordem());
            ordemServico.setTipo_inspecao(ordemServicoDTO.getTipo_inspecao());
            ordemServico.setStatus_ordem(ordemServicoDTO.getStatus_ordem());
            ordemServicoRepository.save(ordemServico);
            return "Ordem de Serviço com ID: " + id + " atualizada com sucesso.";
        } else {
            return "Ordem de Serviço com ID: " + id + " não encontrada.";
        }
    }

}
