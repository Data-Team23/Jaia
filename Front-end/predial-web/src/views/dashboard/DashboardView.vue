<template>
  <div class="chart-container">
    <h2>Dashboard - Requisições/Ordem de Serviço</h2>
    <div class="chart-box">
        <div class="bar-chart-container">
          <bar-chart :chart-data="clienteReq" :options="chartOptions"></bar-chart>
        </div>
        <div class="bar-chart-container">
          <bar-chart :chart-data="funcionarioOs"></bar-chart>
        </div>
    </div>
    <div class="chart-box">
        <div class="pie-chart-container">
          <pie-chart :chart-data="funcionarioOs"></pie-chart>
        </div>
    </div>
  </div>
</template>

<script setup lang="ts">
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
    ArcElement,
} from "chart.js";
import { BarChart, PieChart } from "vue-chart-3";
import "./style.css";
import { ref } from "vue";
import type IFuncionario from "../funcionario/IFuncionario";
import axios from "axios";
import { onMounted } from "vue";
import type IOrdemServico from "../ordemServico/IOrdemServico";
import type IDashboard from "./IDashboard";

let funcionarios = ref<Array<IFuncionario>>([]);
let ordemServicos = ref<Array<IOrdemServico>>([]); 
let funcionarioOs = ref<any>([]);
let clienteReq = ref<any>([]);

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
  BarController,
  PieController,
  ArcElement
);

function getFuncionarioOs(){
  axios.get<any>('http://localhost:8080/dashboard/os-funcionario')
    .then((response: any) => {
      funcionarioOs.value = response.data
    })
}

function getClienteReq(){
  axios.get<any>('http://localhost:8080/dashboard/req-cliente')
    .then((response: any) => {
      clienteReq.value = response.data
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

onMounted(() => {
  getFuncionarioOs()
  getClienteReq()
  listOrdemServicos()
})

</script>
