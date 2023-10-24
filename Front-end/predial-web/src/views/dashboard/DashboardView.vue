<template>
  <div class="chart-container">
    <h2>Dashboard - Requisições/Ordem de Serviço</h2>
    <div class="chart-box">
        <div class="bar-chart-container">
          <bar-chart :chart-data="dataClients" :options="chartOptions"></bar-chart>
        </div>
        <div class="bar-chart-container">
          <bar-chart :chart-data="dataFunc"></bar-chart>
        </div>
    </div>
    <div class="chart-box">
        <div class="pie-chart-container">
          <pie-chart :chart-data="dataFunc"></pie-chart>
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

const dataClients = {
  labels: ["Cliente 1", "Cliente 2", "Cliente 3", "Cliente 4", "Cliente 5"],
  datasets: [
    {
      label: "Requisições",
      backgroundColor: ["#000000", "#2E2E48", "#626288", "#8080BF", "#6A6A69"],
      borderColor: "rgba(75, 192, 192, 1)",
      borderWidth: 1,
      data: [65, 59, 80, 81, 56],
    },
  ],
};

const dataFunc = {
  labels: ["Funcionário 1", "Funcionário 2", "Funcionário 3", "Funcionário 4", "Funcionário 5"],
  datasets: [
    {
      label: "Ordem de Serviço",
      backgroundColor: ["#000000", "#2E2E48", "#626288", "#8080BF", "#6A6A69"],
      borderWidth: 1,
      data: [20, 49, 40, 81, 56],
    },
  ],
};

const chartOptions = {
  indexAxis: "y",
  scales: {
    x: {
      beginAtZero: true,
    },
  },
};
</script>
