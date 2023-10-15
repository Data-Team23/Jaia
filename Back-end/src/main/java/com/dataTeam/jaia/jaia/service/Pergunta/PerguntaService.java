package com.dataTeam.jaia.jaia.service.Pergunta;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataTeam.jaia.jaia.model.Checklist;
import com.dataTeam.jaia.jaia.model.Pergunta;
import com.dataTeam.jaia.jaia.repository.ChecklistRepository;
import com.dataTeam.jaia.jaia.repository.PerguntaRepository;

@Service
public class PerguntaService {

    @Autowired
    private PerguntaRepository perguntaRepo;

    @Autowired
    private ChecklistRepository checklistRepo;

    public Pergunta novaPergunta(Pergunta pergunta, Long checkListId) {

        Optional<Checklist> optChecklist = checklistRepo.findById(checkListId);
        if (optChecklist.isPresent()) {
            Checklist checklist = optChecklist.get();

            checklist.getPerguntas().add(pergunta);

            pergunta = perguntaRepo.save(pergunta);

            checklistRepo.save(checklist);

            return pergunta;
        } else {
            throw new IllegalArgumentException("Checklist não encontrado");
        }
    }

    public Pergunta criarPergunta(Pergunta pergunta){
        return perguntaRepo.save(pergunta);
    }

    public List<Pergunta> buscarTodas() {
        return perguntaRepo.findAll();
    }

}
