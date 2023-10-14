package com.dataTeam.jaia.jaia.service.OrdemServico;

import com.dataTeam.jaia.jaia.model.OrdemServico;
import com.dataTeam.jaia.jaia.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrdemServicoService implements IOrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemRepo;

    @Override
    public OrdemServico novaOrdemServico(OrdemServico ordemServico) {
        return ordemRepo.save(ordemServico);
    }

    public List<OrdemServico> buscarTodasOrdemServico() {
        List<OrdemServico> ordemServico = ordemRepo.findAll();
        return ordemServico;
    }

}
