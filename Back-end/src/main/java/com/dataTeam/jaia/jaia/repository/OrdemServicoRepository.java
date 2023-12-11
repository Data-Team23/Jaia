package com.dataTeam.jaia.jaia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataTeam.jaia.jaia.model.OrdemServico;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long>{
    
    List<OrdemServico> findByFkSupervisorId(Long supervisorId);

}
