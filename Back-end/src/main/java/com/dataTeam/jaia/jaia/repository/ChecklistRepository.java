package com.dataTeam.jaia.jaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.dataTeam.jaia.jaia.model.Checklist;

public interface ChecklistRepository extends JpaRepository<Checklist, Long>{
    
    @Modifying
    @Transactional
    @Query("DELETE FROM Checklist c WHERE c.id = ?1")
    void deleteByIdWithCascade(Long id);
}
