package com.dataTeam.jaia.jaia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dataTeam.jaia.jaia.model.Checklist;

public interface ChecklistRepository extends JpaRepository<Checklist, Long>{
    
}
