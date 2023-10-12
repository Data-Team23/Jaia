package com.dataTeam.jaia.jaia.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.dataTeam.jaia.jaia.model.OrdemServico;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long>{
    
    //  @Query("SELECT o FROM Ordem_Servico o LEFT JOIN FETCH o.checklist WHERE f.id_ordem = :id")
    // Optional<OrdemServico> findByIdWithChecklist(Long id);
}
