package com.dataTeam.jaia.jaia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dataTeam.jaia.jaia.model.OrdemServico;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {

    List<OrdemServico> findByFkSupervisorId(Long supervisorId);

    @Modifying
    @Transactional
    @Query("DELETE FROM OrdemServico f WHERE f.id = ?1")
    void deleteByIdWithCascade(Long id);

}
