package com.dataTeam.jaia.jaia.service.Dashboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataTeam.jaia.jaia.DTO.DashboardDTO;
import com.dataTeam.jaia.jaia.DTO.DatasetsDTO;
import com.dataTeam.jaia.jaia.model.Departamento;
import com.dataTeam.jaia.jaia.model.OrdemServico;
import com.dataTeam.jaia.jaia.model.Requisicao;
import com.dataTeam.jaia.jaia.service.Departamento.DepartamentoService;
import com.dataTeam.jaia.jaia.service.OrdemServico.OrdemServicoService;
import com.dataTeam.jaia.jaia.service.Requisicao.RequisicaoService;

@Service
public class DashboardService {

    @Autowired
    private OrdemServicoService ordemService;

    @Autowired
    private RequisicaoService reqService;

    @Autowired
    private DepartamentoService departService;

    public DashboardDTO getOsByDepartment() {

        DashboardDTO dataDashboard = new DashboardDTO();
        DatasetsDTO datasets = new DatasetsDTO();
        List<DatasetsDTO> datasetsList = new ArrayList<>();
        List<Departamento> departamentos = departService.buscarTodosDepartamentos();
        List<OrdemServico> ordens = ordemService.buscarTodasOrdemServico();
        List<String> labels = new ArrayList<>();
        List<Number> osCountList = new ArrayList<>();
        String[] barColors = { "#A52D10", "#A55F10", "#D78222", "#E89E4C", "#FFBE75", "#FFD2A0" };

        for (Departamento departamento : departamentos) {
            labels.add(departamento.getNome());
            Integer osCount = 0;
            for (OrdemServico os : ordens) {
                Departamento osDepart = os.getId_check().getDepartamento();
                if (osDepart != null && osDepart.getIdDepart() == departamento.getIdDepart()) {
                    osCount += 1;
                }
            }
            osCountList.add(osCount);
        }

        datasets.setData(osCountList);
        datasets.setLabel("Ordens de Serviço");
        datasets.setBorderWidth(1);
        datasets.setBackgroundColor(barColors);
        datasetsList.add(0, datasets);
        dataDashboard.setLabels(labels);
        dataDashboard.setDatasets(datasetsList);

        return dataDashboard;

    }

    public DashboardDTO getReqByMonths() {

        DashboardDTO dataDashboard = new DashboardDTO();
        DatasetsDTO datasets = new DatasetsDTO();
        List<DatasetsDTO> datasetsList = new ArrayList<>();
        List<Requisicao> requisicoes = reqService.buscarTodasRequisicoes();
        List<String> months = new ArrayList<>();
        Map<String, Integer> monthMap;
        List<Number> osCountList = new ArrayList<>();
        String[] barColors = { "#A52D10" };

        months.add("Janeiro");
        months.add("Fevereiro");
        months.add("Março");
        months.add("Abril");
        months.add("Maio");
        months.add("Junho");
        months.add("Julho");
        months.add("Agosto");
        months.add("Setembro");
        months.add("Outubro");
        months.add("Novembro");
        months.add("Dezembro");

        monthMap = new LinkedHashMap<>();
        for (int i = 0; i < months.size(); i++) {
            monthMap.put(months.get(i), i);
        }

        Collections.sort(months, (month1, month2) -> Integer.compare(monthMap.get(month1), monthMap.get(month2)));

        for (int i = 0; i < months.size(); i++) {
            Integer osCount = 0;
            for (Requisicao req : requisicoes) {
                LocalDateTime dataAbertura = req.getData_abertura();
                int mesAbertura = dataAbertura.getMonthValue();
                if (i + 1 == mesAbertura) {
                    osCount += 1;
                }
            }
            osCountList.add(osCount);
        }

        datasets.setData(osCountList);
        datasets.setLabel("Requisições");
        datasets.setBorderWidth(1);
        datasets.setBackgroundColor(barColors);
        datasetsList.add(0, datasets);
        dataDashboard.setLabels(months);
        dataDashboard.setDatasets(datasetsList);

        return dataDashboard;

    }

    public DashboardDTO getOsByStatus() {

        DashboardDTO dataDashboard = new DashboardDTO();
        DatasetsDTO datasets = new DatasetsDTO();
        List<DatasetsDTO> datasetsList = new ArrayList<>();
        List<OrdemServico> ordens = ordemService.buscarTodasOrdemServico();
        List<String> labels = new ArrayList<>();
        List<Number> osCountList = new ArrayList<>();
        String[] barColors = { "#A52D10", "#A55F10", "#D78222", "#E89E4C", "#FFBE75", "#FFD2A0" };

        Map<String, Integer> osCountMap = new HashMap<>();

        for (OrdemServico ordem : ordens) {
            String osStatus = ordem.getStatus_ordem();
            osCountMap.put(osStatus, osCountMap.getOrDefault(osStatus, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : osCountMap.entrySet()) {
            labels.add(entry.getKey());
            osCountList.add(entry.getValue());
        }

        datasets.setData(osCountList);
        datasets.setLabel("Status da ordem");
        datasets.setBorderWidth(1);
        datasets.setBackgroundColor(barColors);
        datasetsList.add(0, datasets);
        dataDashboard.setLabels(labels);
        dataDashboard.setDatasets(datasetsList);

        return dataDashboard;

    }

    public DashboardDTO getOsReprovedByDepartment() {

        DashboardDTO dataDashboard = new DashboardDTO();
        DatasetsDTO datasets = new DatasetsDTO();
        List<DatasetsDTO> datasetsList = new ArrayList<>();
        List<OrdemServico> ordens = ordemService.buscarTodasOrdemServico();
        List<Departamento> departamentos = departService.buscarTodosDepartamentos();
        List<String> labels = new ArrayList<>();
        List<Number> osCountList = new ArrayList<>();
        String[] barColors = { "#A52D10", "#A55F10", "#D78222", "#E89E4C", "#FFBE75", "#FFD2A0" };

        for (Departamento depart : departamentos) {
            Integer osCount = 0;
            labels.add(depart.getNome());
            for (OrdemServico os : ordens) {
                if (os.getId_check().getDepartamento().getIdDepart() == depart.getIdDepart()
                        && os.getStatus_ordem().equals("Reprovada")) {
                    osCount += 1;
                }
            }
            osCountList.add(osCount);
        }

        datasets.setData(osCountList);
        datasets.setLabel("Ordens de Serviço reprovadas");
        datasets.setBorderWidth(1);
        datasets.setBackgroundColor(barColors);
        datasetsList.add(0, datasets);
        dataDashboard.setLabels(labels);
        dataDashboard.setDatasets(datasetsList);

        return dataDashboard;

    }
}
