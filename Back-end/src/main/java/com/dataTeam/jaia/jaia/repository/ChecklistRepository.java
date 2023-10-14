package com.dataTeam.jaia.jaia.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataTeam.jaia.jaia.model.Checklist;

@Repository
public interface ChecklistRepository extends JpaRepository<Checklist, Long>{
    
}
