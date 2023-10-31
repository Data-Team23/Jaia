<template>
    <div class="container">
      <div class="container-top"></div>
      <div class="list-view">
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
                  <th>CNPJ</th>
                  <th>Inspeção</th>
                  <th>Status Requisição</th>
                  <th>Cliente</th>
                  <th></th>
                </tr>
              </thead>
              <tbody v-for="(requisitions, index) in paginatedRequisitions" :key="index">
                <tr>  
                  <td>{{ index + 1 }}</td>
                  <td>{{ requisitions.nome }}</td>
                  <td>{{ requisitions.fk_cliente_id.cnpj }}</td>
                  <td>{{ requisitions.inspecao }}</td>
                  <td>{{ requisitions.status }}</td>
                  <td>{{ requisitions.fk_cliente_id.nome }}</td>
                  <td>
                    <span class="material-symbols-outlined" id= "aprove-button" @click="aproveRequisicao(requisitions.id)"> zoom_out_map </span>
                    <span class="material-symbols-outlined" id="delete-button" @click="deleteDialog = true"> delete </span>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <v-dialog v-model="aproveDialog" width="80%">
        <div class="add-client-container">
          <div class="close-button">
            <span class="material-symbols-outlined" @click="aproveDialog = false"> close  </span>
          </div>
          <AproveRequisicaoForm></AproveRequisicaoForm>
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
  import AproveRequisicaoForm from './AproveRequisicaoForm.vue';
  import { computed, onMounted, ref, watch } from "vue";
  import type IRequisition from "../IRequisition";
  import router from "@/router";
  
  
  let aproveDialog = ref(false);
  let deleteDialog = ref(false);
  
  let requisitions = ref<Array<IRequisition>>([])
  
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
          label: "CNPJ",
          value: "cnpj"
      },
      {
          label: "Status OS.",
          value: "status_os"
      },
      {
          label: "Status Req.",
          value: "status_req"
      }
  ]
  
  function listRequisitions() {
      axios.get<any>('http://localhost:8080/requisicao')
          .then((response: any) => {
              requisitions.value = response.data
              filteredRequisitions.value = requisitions.value;
              filterRequisitions();
          })
  }
  
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
  
  function clearUrlParam(newValue: boolean) {
    if (!newValue && router.currentRoute.value.query.id !== undefined) {
      router.push({ query: { ...router.currentRoute.value.query, id: undefined } });
    }
  }
  
  function aproveRequisicao(id: number) {
      router.push({query: { id: id }})
      aproveDialog.value = true
  }


  
  </script>
  