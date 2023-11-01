package com.dataTeam.jaia.jaia.service.Dashboard;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataTeam.jaia.jaia.DTO.DashboardDTO;
import com.dataTeam.jaia.jaia.DTO.DatasetsDTO;
import com.dataTeam.jaia.jaia.model.Cliente;
import com.dataTeam.jaia.jaia.model.Departamento;
import com.dataTeam.jaia.jaia.model.Funcionario;
import com.dataTeam.jaia.jaia.model.OrdemServico;
import com.dataTeam.jaia.jaia.model.Requisicao;
import com.dataTeam.jaia.jaia.service.Cliente.ClienteService;
import com.dataTeam.jaia.jaia.service.Departamento.DepartamentoService;
import com.dataTeam.jaia.jaia.service.Funcionario.FuncionarioService;
import com.dataTeam.jaia.jaia.service.OrdemServico.OrdemServicoService;
import com.dataTeam.jaia.jaia.service.Requisicao.RequisicaoService;

@Service
public class DashboardService {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private OrdemServicoService ordemService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private RequisicaoService reqService;

    @Autowired
    private DepartamentoService departService;

    public DashboardDTO getFuncionarioOS() {
        List<Funcionario> funcionarios = funcionarioService.buscarTodosFuncionario();
        List<OrdemServico> ordens = ordemService.buscarTodasOrdemServico();
        List<String> labels = new ArrayList<>();
        List<Number> osCountList = new ArrayList<>();
        String[] barColors = { "#000000", "#2E2E48", "#626288", "#8080BF", "#6A6A69" };
        DashboardDTO dataDashboard = new DashboardDTO();
        DatasetsDTO datasets = new DatasetsDTO();
        List<DatasetsDTO> datasetsList = new ArrayList<>();
        for (Funcionario funcionario : funcionarios) {
            labels.add(funcionario.getNome());
            Integer osCount = 0;
            for (OrdemServico os : ordens) {
                if (os.getId_supervisor().getId() == funcionario.getId()) {
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

    public DashboardDTO getClienteReq() {

        List<Cliente> clientes = clienteService.buscarTodosClientes();
        List<Requisicao> requisicoes = reqService.buscarTodasRequisicoes();
        List<String> labels = new ArrayList<>();
        List<Number> reqCountList = new ArrayList<>();
        String[] barColors = { "#000000", "#2E2E48", "#626288", "#8080BF", "#6A6A69" };
        DatasetsDTO datasets = new DatasetsDTO();
        List<DatasetsDTO> datasetsList = new ArrayList<>();
        DashboardDTO dataDashboard = new DashboardDTO();

        for (Cliente cliente : clientes) {
            labels.add(cliente.getNome());
            Integer reqCount = 0;
            for (Requisicao requisicao : requisicoes) {
                if (cliente.getId() == requisicao.getFk_cliente_id().getId()) {
                    reqCount += 1;
                }
            }
            reqCountList.add(reqCount);
        }
        datasets.setData(reqCountList);
        datasets.setLabel("Requisições");
        datasets.setBorderWidth(1);
        datasets.setBackgroundColor(barColors);
        datasetsList.add(0, datasets);
        dataDashboard.setLabels(labels);
        dataDashboard.setDatasets(datasetsList);

        return dataDashboard;
    }

    public DashboardDTO getOsByDepartment() {

        DashboardDTO dataDashboard = new DashboardDTO();
        DatasetsDTO datasets = new DatasetsDTO();
        List<DatasetsDTO> datasetsList = new ArrayList<>();
        List<Departamento> departamentos = departService.buscarTodosDepartamentos();
        List<OrdemServico> ordens = ordemService.buscarTodasOrdemServico();
        List<String> labels = new ArrayList<>();
        List<Number> osCountList = new ArrayList<>();
        String[] barColors = { "#000000", "#2E2E48", "#626288", "#8080BF", "#6A6A69" };

        for (Departamento departamento : departamentos) {
            labels.add(departamento.getNome());
            Integer osCount = 0;
            for(OrdemServico os : ordens){
                Departamento osDepart = os.getId_check().getDepartamento();
                // if(osDepart.size() > 0 && osDepart.get(0).getCodDepart() == departamento.getCodDepart()){
                //     osCount += 1;
                // }
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

    public DashboardDTO getOsAllTime() {

        DashboardDTO dataDashboard = new DashboardDTO();

        return dataDashboard;

    }

    public DashboardDTO getOsByStatus() {

        DashboardDTO dataDashboard = new DashboardDTO();

        return dataDashboard;

    }

    public DashboardDTO getOsApprovedByDepartment(){

        DashboardDTO dataDashboard = new DashboardDTO();

        return dataDashboard;

    }
}
