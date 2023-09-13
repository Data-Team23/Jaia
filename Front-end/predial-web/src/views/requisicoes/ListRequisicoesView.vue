<template>
  <div class="container">
    <div class="container-top"></div>
    <div class="list-view">
      <div class="add-client-button-container">
        <div class="add-client-button">
          <button @click="addDialog = true">Adicionar Requisicoes</button>
        </div>
      </div>
      <div class="list-container">
        <div class="top-list">
          <h3><strong>Carteira de Requisicoes</strong></h3>
          <div class="search-input">
            <input type="text" />
          </div>
        </div>
        <div class="table-container">
          <table>
            <thead>
              <tr>
                <th>No.</th>
                <th>Nome</th>
                <th>Data Abertura</th>
                <th>Status</th>
                <th>Descrição</th>
              </tr>
            </thead>
            <tbody v-for="(requisicao, index) in requisicoes" :key="index">
              <tr>  
                <td>{{ index + 1 }}</td>
                <td>{{ requisicao.nome }}</td>
                <td>{{ requisicao.dataAbertura }}</td>
                <td>{{ requisicao.status }}</td>
                <td>{{ requisicao.descricao }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
    <v-dialog v-model="addDialog" width="80%">
      <div class="add-client-container">
        <div class="close-button">
          <span class="material-symbols-outlined" @click="addDialog = false"> close </span>
        </div>
        <AddRequisicoesForm></AddRequisicoesForm>
      </div>
    </v-dialog>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import "./styles.css";
import AddRequisicoesForm from "./AddRequisicoesView.vue";

let addDialog = ref(false);
let editDialog = ref(false);
let deleteDialog = ref(false);

const requisicoes = [
  {
    code: '001',
    nome: 'Requisição 1',
    dataAbertura: '2023-09-12',
    status: 'Criação',
    descricao: 'Descrição da Requisição 1',
  },
  {
    code: '002',
    nome: 'Requisição 2',
    dataAbertura: '2023-09-13',
    status: 'Em Criação',
    descricao: 'Descrição da Requisição 2',
  },
];
</script>
