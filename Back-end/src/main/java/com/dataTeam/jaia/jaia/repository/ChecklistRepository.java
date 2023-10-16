package com.dataTeam.jaia.jaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import com.dataTeam.jaia.jaia.model.Checklist;

@Repository
public interface ChecklistRepository extends JpaRepository<Checklist, Long>{
    
    @Modifying
    @Transactional
    @Query("DELETE FROM Checklist c WHERE c.id = ?1")
    void deleteByIdWithCascade(Long id);
}
