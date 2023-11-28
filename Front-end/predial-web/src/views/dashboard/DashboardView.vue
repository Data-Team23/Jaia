<template>
  <div class="dashboard-container">
    <div class="chart-container">
      <div class="title">
        <h3>Ordens de Serviço por Departamento</h3>
      </div>
      <div class="chart-box">
        <div class="bar-chart-container">
          <bar-chart :chart-data="departamentoOs" :options="chartVerticalOptions"></bar-chart>
        </div>      
      </div>
    </div>
  
    <div class="duo-chart">
      <div class="chart-container" style="width: 75% !important;">
        <div class="title">
          <h3>Ordens de Serviço por Status</h3>
        </div>
        <div class="duo-chart-box">   
          <div class="pie-chart-container">
            <pie-chart :chart-data="statusOs"></pie-chart>
          </div>
        </div>  
      </div>
      <div class="chart-container">
        <div class="title">
          <h3>Ordens de Serviço Reprovadas por Departamento</h3>
        </div>
        <div class="duo-chart-box">
          <div class="bar-chart-container">
            <bar-chart :chart-data="osReprovedByDepartment" :options="chartOptions"></bar-chart>
          </div> 
        </div>
      </div>
    </div>
  
    <div class="chart-container" style="margin-top: 20px;">
      <div class="title">
        <h3>Requisições de Clientes ao longo do ano</h3>
      </div>
      <div class="chart-box">
        <div class="pie-chart-container">
          <line-chart :chart-data="reqByMonths" :options="chartLineOptions" :height="300"></line-chart>
        </div>
      </div>
      <br>   
    </div>
  </div>
</template>

<script setup lang="ts">
import axios from "axios";
import {
    Chart as ChartJS,
    Title,
    Tooltip,
    Legend,
    BarElement,
    CategoryScale,
    LinearScale,
    BarController,
    PieController,
    LineController,
    ArcElement,
PointElement,
LineElement,
} from "chart.js";
import { BarChart, PieChart, LineChart } from "vue-chart-3";
import { ref } from "vue";
import { onMounted } from "vue";
import type IOrdemServico from "../ordemServico/IOrdemServico";
import "./style.css";

let ordemServicos = ref<Array<IOrdemServico>>([]); 
let departamentoOs = ref<any>([]);
let statusOs = ref<any>([]);
let osReprovedByDepartment = ref<any>([]);
let reqByMonths = ref<any>([]);

ChartJS.register(
  Title,
  Tooltip,
  Legend,
  CategoryScale,
  LinearScale,
  BarElement,
  ArcElement,
  PointElement, 
  LineElement,
  BarController,
  PieController,
  LineController
);

function getDepartamentoOs(){
  axios.get<any>('http://localhost:8080/dashboard/os-departamento')
    .then((response: any) => {
      const chartData = response.data

      // Obtém os dados e os rótulos
      const data = chartData.datasets[0].data;
      const labels = chartData.labels;

      // Cria um array de objetos {label, data} para facilitar a ordenação
      const dataWithLabels = labels.map((label: any, index: any) => ({ label, data: data[index] }));

      // Ordena o array com base nos dados em ordem decrescente
      dataWithLabels.sort((a: any, b: any) => b.data - a.data);

      // Atualiza os rótulos e dados no objeto chartData
      chartData.labels = dataWithLabels.map((item: any) => item.label);
      chartData.datasets[0].data = dataWithLabels.map((item: any) => item.data);

      // Atualiza o estado
      departamentoOs.value = chartData;
      console.log(departamentoOs.value);
    })
}

function getStatusOs(){
  axios.get<any>('http://localhost:8080/dashboard/os-status')
    .then((response: any) => {
      statusOs.value = response.data
    })
}

function getOsReprovedByDepartment(){
  axios.get<any>('http://localhost:8080/dashboard/os-reprovada')
    .then((response: any) => {
      const chartData = response.data

      const data = chartData.datasets[0].data;
      const labels = chartData.labels;

      const dataWithLabels = labels.map((label: any, index: any) => ({ label, data: data[index] }));

      dataWithLabels.sort((a: any, b: any) => b.data - a.data);

      chartData.labels = dataWithLabels.map((item: any) => item.label);
      chartData.datasets[0].data = dataWithLabels.map((item: any) => item.data);

      osReprovedByDepartment.value = chartData
    })
}

function getReqByMonths(){
  axios.get<any>('http://localhost:8080/dashboard/os-time')
    .then((response: any) => {
      reqByMonths.value = response.data
    })
}

function listOrdemServicos() {
    axios.get<any>('http://localhost:8080/ordem-servico') 
        .then((response: any) => {
            ordemServicos.value = response.data
        })
        .catch((error: any) => {
            console.error('Erro ao buscar funcionários:', error);
        });
}

const chartOptions = {
  indexAxis: "y",
  scales: {
    x: {
      beginAtZero: true,
    },
  },
  plugins: {
    legend: {
      display: false,
    },
  },
};

const chartVerticalOptions = {
  plugins: {
    legend: {
      display: false,
    },
  },
}

const chartLineOptions = ref({
  responsive: true,
  maintainAspectRatio: false,
  scales: {
    y: {
      beginAtZero: true
    }
  },
  elements: {
    line: {
      borderColor: "#3A3A5A",
    },
  },
  plugins: {
    legend: {
      display: false,
    },
  },
});

onMounted(() => {
  getDepartamentoOs()
  getStatusOs()
  getOsReprovedByDepartment()
  getReqByMonths()
  listOrdemServicos()
})

</script>
