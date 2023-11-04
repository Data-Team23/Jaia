package com.dataTeam.jaia.jaia.DTO;

import java.util.List;

import lombok.Data;

@Data
public class DashboardDTO {
    private List<String> labels;
    private List<DatasetsDTO> datasets;
}
