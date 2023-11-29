<template>
  <div class="container">
    <div class="container-top"></div>
    <div class="list-view">
      <div class="add-client-button-container">
        <div class="add-client-button">
          <button @click="addDialog = true">CRIAR</button>
        </div>
      </div>
      <div class="list-container">
        <div class="top-list">
          <h3><strong>Requisições</strong></h3>
          <div class="search-filter">
            <SelectField
              :option-values="filterSelectOptions"
              v-model="selectedFilter"
              value-prop="value"
              display-prop="label"
            >
            </SelectField>
            <input type="text" placeholder="Filtrar..." v-model="filterInput" />
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
            <tbody v-for="(requisicao, index) in paginatedRequisitions" :key="index">
              <tr>
                <td>{{ requisicao.no }}</td>
                <td>{{ requisicao.nome }}</td>
                <td>{{ requisicao.data_abertura }}</td>
                <td>{{ requisicao.status }}</td>
                <td>{{ requisicao.descricao }}</td>
              </tr>
            </tbody>
          </table>
          <div class="pagination">
            <ul class="pagination-list">
              <li v-for="pageNumber in totalPages" :key="pageNumber" @click="changePage(pageNumber)">
                <a :class="{ active: page === pageNumber }">{{ pageNumber }}</a>
              </li>
            </ul>
          </div>
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
    <v-dialog v-model="editDialog" width="80%">
      <div class="add-client-container">
        <div class="close-button">
          <span class="material-symbols-outlined" @click="editDialog = false"> close </span>
        </div>
        <UpdateRequisicoesForm></UpdateRequisicoesForm>
      </div>
    </v-dialog>
    <v-dialog v-model="deleteDialog" width="30%">
      <div class="delete-client-container">
        <h2>Tem certeza que deseja excluir ?</h2>
        <br />
        <div class="confirm-delete-button">
          <InputButton text-button="Sim" @click="deleteDialog = false"></InputButton>
          <InputButton text-button="Não" @click="deleteDialog = false"></InputButton>
        </div>
      </div>
    </v-dialog>
  </div>
</template>

<script setup lang="ts">
import "../styles.css";

import InputButton from "@/components/Button/InputButton.vue";
import SelectField from "@/components/Select/SelectField.vue";
import axios from "axios";
import { computed, onMounted, ref, watch } from "vue";
import type IRequisition from "../IRequisition";
import AddRequisicoesForm from "./AddRequisicoesForm.vue";
import UpdateRequisicoesForm from "./UpdateRequisicoesView.vue";

let addDialog = ref(false);
let editDialog = ref(false);
let deleteDialog = ref(false);

//let requisitions = ref<Array<IRequisition>>([])

const requisitions = ref<Array<IRequisition>>([]);

let filteredRequisitions = ref<Array<IRequisition>>([])
let filterInput = ref("")
let selectedFilter = ref("nome")

let paginatedRequisitions = ref<Array<IRequisition>>([]);

const page = ref(1);
const itemsPerPage = ref(5);

const filterSelectOptions = [
    {
        label: "Nome",
        value: "nome"
    },
    {
        label: "Data",
        value: "data_abertura"
    },
    {
        label: "Status",
        value: "status"
    }
]

function listRequisitions() {
    axios.get<any>(`http://localhost:8080/requisicao`)
        .then((response: any) => {
            requisitions.value = response.data
            filteredRequisitions.value = requisitions.value;
            requisitions.value.forEach((requisicao, index) => {
                requisicao.no = index + 1
            })
            filterRequisitions();
        })
}

// async function listRequisitions() {
//   try {
//     requisitions.value = (await axios.get('http://localhost:8080/requisicao')).data;
//   } catch (error) {
//     console.error('Erro ao obter requisições:', error);
//   }
// }




function filterRequisitions() {
    filteredRequisitions.value = requisitions.value.filter((client: any) => {
        const selectedValue = client[selectedFilter.value];
        totalPages = computed(() => Math.ceil(filteredRequisitions.value.length / itemsPerPage.value));
        return selectedValue.toLowerCase().includes(filterInput.value.toLowerCase());
    })
    paginate()
}

const paginate = () => {
    const startIndex = (page.value - 1) * itemsPerPage.value;
    const endIndex = startIndex + itemsPerPage.value;

    paginatedRequisitions.value = filteredRequisitions.value.slice(startIndex, endIndex);
}

let totalPages = computed(() => Math.ceil(requisitions.value.length / itemsPerPage.value));

onMounted(() => {
    listRequisitions();
})

watch(filterInput, filterRequisitions)

watch(page, (newPage) => {
    paginate();
});

const changePage = (pageNumber: any) => {
    page.value = pageNumber;
};
</script>