package com.dataTeam.jaia.jaia.DTO;

import lombok.Data;

@Data
public class OrdemServicoDTO {
    
    private String nome_ordem;
    private String tipo_inspecao;
    private String status_ordem;
    private Long idSupervisor;
    private Long idCliente;
    private Long idChecklist;

}
