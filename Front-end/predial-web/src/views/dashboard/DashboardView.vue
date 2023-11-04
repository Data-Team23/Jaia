<template>
  <div class="chart-container">
    <h2>Dashboard - Requisições/Ordem de Serviço</h2>
    <div class="chart-box">
        <div class="bar-chart-container">
          <bar-chart :chart-data="departamentoOs"></bar-chart>
        </div>
        <div class="bar-chart-container">
          <bar-chart :chart-data="osReprovedByDepartment" :options="chartOptions"></bar-chart>
        </div>
    </div>
    <br>
    <div class="chart-box">
        <div class="pie-chart-container">
          <pie-chart :chart-data="statusOs"></pie-chart>
        </div>
        <div class="pie-chart-container">
          <line-chart :chart-data="reqByMonths" :options="chartLineOptions"></line-chart>
        </div>
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
      departamentoOs.value = response.data
    })
}

function getStatusOs(){
  axios.get<any>('http://localhost:8080/dashboard/os-status')
    .then((response: any) => {
      statusOs.value = response.data
      console.log(response.data)
    })
}

function getOsReprovedByDepartment(){
  axios.get<any>('http://localhost:8080/dashboard/os-reprovada')
    .then((response: any) => {
      osReprovedByDepartment.value = response.data
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
};

const chartLineOptions = ref({
  responsive: true,
  maintainAspectRatio: false,

  elements: {
    line: {
      borderWidth: 2,
      borderColor: "#3A3A5A",
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
