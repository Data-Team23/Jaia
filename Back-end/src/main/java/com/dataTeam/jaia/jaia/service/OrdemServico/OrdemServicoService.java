package com.dataTeam.jaia.jaia.service.OrdemServico;

import com.dataTeam.jaia.jaia.model.OrdemServico;
import com.dataTeam.jaia.jaia.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class OrdemServicoService implements IOrdemServicoService {

    // @Autowired
    // private OrdemServicoRepository ordemRepo;

    // @Transactional
    // public OrdemServico novaOrdemServico(OrdemServico ordemServico) {
    //     return ordemRepo.save(ordemServico);
    // }

    // public List<OrdemServico> buscarTodasOrdemServico() {
    //     List<OrdemServico> ordemServico = ordemRepo.findAll();
    //     return ordemServico;
    // }

    // public OrdemServico buscarPorId(Long id) {
    //     Optional<OrdemServico> ordemOP = ordemRepo.findByIdWithChecklist(id);
    //     if (ordemOP.isEmpty()) {
    //         throw new IllegalArgumentException("Ordem de Serviço nao encontrado!");
    //     }
    //     OrdemServico ordemServico = ordemOP.get();
    //     return ordemServico;
    // }

}
