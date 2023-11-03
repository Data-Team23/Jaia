<template>
  <div class="container">
      <div class="container-top">

      </div>
      <div class="list-view">
          <div class="add-client-button-container">
              <div class="add-client-button">
                  <button @click="addDialog = true">
                      Adicionar departamento
                  </button>
              </div>
          </div>
          <div class="list-container">
              <div class="top-list">
                  <h3><strong>Lista de departamentos</strong></h3>
                  <div class="search-filter">
                      <SelectField 
                          :option-values="filterSelectOptions" 
                          v-model="selectedFilter" 
                          value-prop="value" 
                          display-prop="label">
                      </SelectField>
                      <input type="text" placeholder="Filtrar..." v-model="filterInput">
                  </div>
              </div>
              <div class="table-container">
                  <table>
                      <thead>
                          <tr>
                              <th>No.</th>
                              <th>Código</th>
                              <th>Nome</th>
                              <th></th>
                          </tr>
                      </thead>
                      <tbody v-for="(departament, index) in paginatedDepartaments" :key="index">
                          <tr>
                              <td>{{ index + 1 }}</td>
                              <td>{{ departament.idDepart }}</td>
                              <td>{{ departament.nome }}</td>
                              <td>
                                  <span class="material-symbols-outlined" id="edit-button" @click="editDepartamento(departament.idDepart)">
                                      edit
                                  </span>
                                  <span class="material-symbols-outlined" id="delete-button" @click="deleteDialog = true">
                                      delete
                                  </span>
                              </td>
                          </tr>
                      </tbody>
                  </table>
                  <div class="pagination">
                      <ul class="pagination-list">
                          <li v-for="pageNumber in totalPages" :key="pageNumber" @click="changePage(pageNumber)">
                              <a :class="{ active: page === pageNumber }">
                                  {{ pageNumber }}
                              </a>
                          </li>
                      </ul>
                  </div>
              </div>
          </div>
      </div>
      <v-dialog v-model="addDialog" width="80%">
          <div class="add-client-container">
              <div class="close-button">
                  <span class="material-symbols-outlined" @click="addDialog = false">
                      close
                  </span>
              </div>
              <AddDepartamentForm></AddDepartamentForm>
          </div>
      </v-dialog>
      <v-dialog v-model="editDialog" width="80%">
          <div class="add-client-container">
              <div class="close-button">
                  <span class="material-symbols-outlined" @click="editDialog = false">
                      close
                  </span>
              </div>
              <EditDepartamentForm></EditDepartamentForm>
          </div>
      </v-dialog>
      <v-dialog v-model="deleteDialog" width="30%">
          <div class="delete-client-container">
              <h2>Tem certeza que deseja excluir ?</h2>
              <br>
              <div class="confirm-delete-button">
                  <InputButton text-button="Sim" @click="deleteDialog = false"></InputButton>
                  <InputButton text-button="Não" @click="deleteDialog = false"></InputButton>
              </div>
          </div>
      </v-dialog>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, type Ref, watch, computed } from 'vue';
import axios from 'axios';
import AddDepartamentForm from "./AddDepartmentView.vue";
import EditDepartamentForm from "./UpdateDepartmentView.vue";
import InputButton from '@/components/Button/InputButton.vue';
import SelectField from '@/components/Select/SelectField.vue';
import '../styles/form-styles.css'
import '../styles/table-styles.css'
import '../styles/dialog-styles.css'
import type IDepartament from './IDepartament'
import { useRouter } from 'vue-router';

let addDialog = ref(false)
let editDialog = ref(false)
let deleteDialog = ref(false)

const router = useRouter();

let departaments = ref<Array<IDepartament>>([])

let filteredDepartaments = ref<Array<IDepartament>>([])
let filterInput = ref("")
let selectedFilter = ref("nome")

let paginatedDepartaments = ref<Array<IDepartament>>([]);

const filterSelectOptions = [
  {
      label: "Nome",
      value: "nome"
  },
  {
      label: "Código",
      value: "codigo"
  },
  {
      label: "Responsável",
      value: "responsavel"
  },
]

const page = ref(1);
const itemsPerPage = ref(5);

function editDepartamento(codDepart: number) {
    router.push({query: { codDepart: codDepart }})
    editDialog.value = true
}

function clearUrlParam(newValue: boolean) {
  if (!newValue && router.currentRoute.value.query.codDepart !== undefined) {
    router.push({ query: { ...router.currentRoute.value.query, codDepart: undefined } });
  }
}

function listDepartaments() {
  axios.get<any>('http://localhost:8080/departamentos') 
      .then((response: any) => {
          departaments.value = response.data
          filteredDepartaments.value = departaments.value;
          filterDepartaments();
      })
      .catch((error: any) => {
          console.error('Erro ao buscar departamentos:', error);
      });
}

function filterDepartaments() {
  filteredDepartaments.value = departaments.value.filter((departament: any) => {
      const selectedValue = departament[selectedFilter.value];
      totalPages = computed(() => Math.ceil(filteredDepartaments.value.length / itemsPerPage.value));
      return selectedValue.toLowerCase().includes(filterInput.value.toLowerCase());
  })
  paginate()
}

const paginate = () => {
  const startIndex = (page.value - 1) * itemsPerPage.value;
  const endIndex = startIndex + itemsPerPage.value;

  paginatedDepartaments.value = filteredDepartaments.value.slice(startIndex, endIndex);
}

let totalPages = computed(() => Math.ceil(departaments.value.length / itemsPerPage.value));

onMounted(() => {
  listDepartaments();
})

watch(editDialog, clearUrlParam)

watch(filterInput, filterDepartaments)

watch(page, (newPage) => {
  paginate();
});

const changePage = (pageNumber: any) => {
  page.value = pageNumber;
};
</script>
