package com.dataTeam.jaia.jaia.DTO;

import java.util.List;

import lombok.Data;

@Data
public class DatasetsDTO {
    private String label;
    private String[] backgroundColor;
    private Number borderWidth;
    private List<Number> data;
}
