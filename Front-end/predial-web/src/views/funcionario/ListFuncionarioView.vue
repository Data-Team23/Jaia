<template>
  <div class="container">
    <div class="container-top"></div>
    <div class="list-view">
      <div class="add-client-button-container">
        <div class="add-client-button">
          <button @click="addDialog = true">Adicionar funcionário</button>
        </div>
      </div>
      <div class="list-container">
        <div class="top-list">
          <h3><strong>Carteira de funcionários</strong></h3>
          <div class="search-filter">
            <SelectField :option-values="filterSelectOptions" v-model="selectedFilter" value-prop="value"
              display-prop="label"></SelectField>
            <input type="text" placeholder="Filtrar..." v-model="filterInput">
          </div>
        </div>
        <div class="table-container">
          <table>
            <thead>
              <tr>
                <th>No.</th>
                <th>Nome</th>
                <th>CPF</th>
                <th>Departamento</th>
                <th>E-mail</th>
                <th></th>
              </tr>
            </thead>
            <tbody v-for="(funcionario, index) in paginatedFuncionarios" :key="index">
              <tr>
                <td>{{ index + 1 }}</td>
                <td>{{ funcionario.nome }}</td>
                <td>{{ funcionario.cpf }}</td>
                <td>
                  <span v-if="funcionario.departamento">{{ funcionario.departamento.nome }}</span>
                  <span v-else>Não informado</span>
                </td>
                <td>{{ funcionario.email }}</td>
                <td>
                  <span class="material-symbols-outlined" id="edit-button" @click="editFuncionario(funcionario.id)"> edit
                  </span>
                  <span class="material-symbols-outlined" id="delete-button" @click="deleteDialog = true"> delete </span>
                </td>
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
        <AddFuncionarioForm></AddFuncionarioForm>
      </div>
    </v-dialog>
    <v-dialog v-model="editDialog" width="80%">
      <div class="add-client-container">
        <div class="close-button">
          <span class="material-symbols-outlined" @click="editDialog = false"> close </span>
        </div>
        <UpdateFuncionarioForm></UpdateFuncionarioForm>
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
import { ref, onMounted, computed, watch } from 'vue';
import AddFuncionarioForm from "./AddFuncionarioView.vue";
import UpdateFuncionarioForm from "./UpdateFuncionarioView.vue";
import InputButton from '@/components/Button/InputButton.vue';
import SelectField from '@/components/Select/SelectField.vue';
import axios from 'axios';
import type IFuncionario from './IFuncionario';
import { useRouter } from 'vue-router';
import type { Ref } from 'vue';

const router = useRouter()

let addDialog = ref(false);
let editDialog = ref(false);
let deleteDialog = ref(false);

let funcionarios = ref<Array<IFuncionario>>([]);
let paginatedFuncionarios = ref<Array<IFuncionario>>([]);
let filteredFuncionarios = ref<Array<IFuncionario>>([]);
let filterInput = ref("");
let selectedFilter = ref("nome");

const filterSelectOptions = [
  {
    label: "Nome",
    value: "nome"
  },
  {
    label: "CPF",
    value: "cpf"
  },
  {
    label: "Departamento",
    value: "departamento"
  },
];

const page = ref(1);
const itemsPerPage = ref(5);

function editFuncionario(id: number) {
  router.push({ query: { id: id } })
  editDialog.value = true
}

function clearUrlParam(newValue: boolean) {
  if (!newValue && router.currentRoute.value.query.id !== undefined) {
    router.push({ query: { ...router.currentRoute.value.query, id: undefined } });
  }
}

function listFuncionarios() {
  axios.get<IFuncionario>('http://localhost:8080/funcionario')
    .then((response: any) => {
      funcionarios.value = response.data
      filterFuncionarios(response.data)
    })
    .catch((error: any) => {
      console.error('Erro ao buscar funcionários:', error);
    });
}

function filterFuncionarios(list: any) {


  filteredFuncionarios.value = list.filter((funcionario: any) => {
    const selectedValue = funcionario[selectedFilter.value];
    totalPages = computed(() => Math.ceil(filteredFuncionarios.value.length / itemsPerPage.value));
    return selectedValue.toLowerCase().includes(filterInput.value.toLowerCase());
  })
  paginate(filteredFuncionarios.value)
}

const paginate = (list: any) => {
  const startIndex = (page.value - 1) * itemsPerPage.value;
  const endIndex = startIndex + itemsPerPage.value;
  paginatedFuncionarios.value = list.slice(startIndex, endIndex);
  console.log(paginatedFuncionarios.value[2].departamento?.nome)
  console.log(paginatedFuncionarios.value[1].departamento?.nome)
  console.log(paginatedFuncionarios.value[0].departamento?.nome)
}

let totalPages = computed(() => Math.ceil(funcionarios.value.length / itemsPerPage.value));

onMounted(() => {
  listFuncionarios();
})

watch(editDialog, clearUrlParam)

watch(filterInput, filterFuncionarios)

watch(page, (newPage) => {
  paginate(filteredFuncionarios);
});

const changePage = (pageNumber: any) => {
  page.value = pageNumber;
};

</script>
