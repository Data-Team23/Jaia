<template>
  <div class="container">
    <div class="container-top"></div>
    <div class="list-view">
      <div class="add-client-button-container">
        <div class="add-client-button">
          <button @click="addDialog = true">Cadastrar Ordem de Serviço</button>
        </div>
      </div>
      <div class="list-container">
        <div class="top-list">
          <h3><strong>Visualizar Ordem de Serviço</strong></h3>
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
                <th>CNPJ</th>
                <th>Inspeção</th>
                <th>Status O.S.</th>
                <th>Departamento</th>
                <th></th>
              </tr>
            </thead>
            <tbody v-for="(OrdemServico, index) in paginatedOrdemServicos" :key="index">
              <tr>  
                <td>{{ index + 1 }}</td>
                  <td>{{ OrdemServico.nome_ordem }}</td>
                  <td>{{ OrdemServico.id_req.data_abertura }}</td>
                  <td>{{ OrdemServico.id_req.fkCliente.cnpj ? OrdemServico.id_req.fkCliente.cnpj : "Não informado" }}</td>
                  <td>{{ OrdemServico.tipo_inspecao }}</td>
                  <td>{{ OrdemServico.status_ordem }}</td>
                  <td>{{ OrdemServico.id_check.departamento.nome ?? "Não informado" }}</td>
                <td>
                  <span class="material-symbols-outlined" id="edit-button" @click="editOrdemServico(OrdemServico.id)"> edit </span>
                  <span class="material-symbols-outlined" id= "aprove-button" @click="aproveOrdemServico(OrdemServico.id)"> zoom_out_map </span>
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
        <AddOrdemServicoForm></AddOrdemServicoForm>
      </div>
    </v-dialog>
    <v-dialog v-model="editDialog" width="80%" class="update-os">
      <div class="add-client-container">
        <div class="close-button">
          <span class="material-symbols-outlined" @click="editDialog = false"> close </span>
        </div>
        <UpdateOrdemServicoForm></UpdateOrdemServicoForm>
      </div>
    </v-dialog>
    <v-dialog v-model="aproveDialog" width="80%">
      <div class="add-client-container">
        <div class="close-button">
          <span class="material-symbols-outlined" @click="aproveDialog = false"> close  </span>
        </div>
        <AproveOrdemServicoForm></AproveOrdemServicoForm>
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
  <button class="button" @click="logout">Sair</button>
</template>

<script setup lang="ts">
import { ref, onMounted, computed, watch } from 'vue';
import AddOrdemServicoForm from "./AddOrdemServicoView.vue";
import UpdateOrdemServicoForm from "./UpdateOrdemServicoView.vue";
import AproveOrdemServicoForm from './AproveOrdemServicoView.vue';
import InputButton from '@/components/Button/InputButton.vue';
import SelectField from '@/components/Select/SelectField.vue';
import axios from 'axios';
import type IOrdemServico from '../IOrdemServico';
import { useRouter } from 'vue-router';
import { useAuthStore } from '@/stores/authStore';

const router = useRouter()

const authStore = useAuthStore();

let addDialog = ref(false);
let editDialog = ref(false);
let deleteDialog = ref(false);
let aproveDialog = ref(false);

let OrdemServicos = ref<Array<IOrdemServico>>([]); 
let paginatedOrdemServicos = ref<Array<IOrdemServico>>([]); 
let filteredOrdemServicos = ref<Array<IOrdemServico>>([]); 
let filterInput = ref("");
let selectedFilter = ref("nome_ordem");

const filterSelectOptions = [
  {
    label: "Nome",
    value: "nome_ordem",
  },
  {
    label: "Status",
    value: "status_ordem",
  },
  {
    label: "Insepeção",
    value: "tipo_inspecao",
  },
];

const page = ref(1);
const itemsPerPage = ref(5);

function editOrdemServico(id: number) {
    router.push({query: { id: id }})
    editDialog.value = true
}

function aproveOrdemServico(id: number) {
    router.push({query: { id: id }})
    aproveDialog.value = true
}


function clearUrlParam(newValue: boolean) {
  if (!newValue && router.currentRoute.value.query.id !== undefined) {
    router.push({ query: { ...router.currentRoute.value.query, id: undefined } });
  }
}


function listOrdemServicos() {
    const id_supervisor = authStore.userId
    axios.get<any>(`http://localhost:8080/ordem-servico/supervisor/${id_supervisor}`) 
        .then((response: any) => {
            OrdemServicos.value = response.data
            console.log(response.data)
            filteredOrdemServicos.value = OrdemServicos.value;
            filterOrdemServicos();
        })
        .catch((error: any) => {
            console.error('Erro ao buscar funcionários:', error);
        });
}

function filterOrdemServicos() {
    filteredOrdemServicos.value = OrdemServicos.value.filter((OrdemServico: any) => {
        const selectedValue = OrdemServico[selectedFilter.value];
        totalPages = computed(() => Math.ceil(filteredOrdemServicos.value.length / itemsPerPage.value));
        return selectedValue.toLowerCase().includes(filterInput.value.toLowerCase());
    })
    paginate()
}

const paginate = () => {
    const startIndex = (page.value - 1) * itemsPerPage.value;
    const endIndex = startIndex + itemsPerPage.value;

    paginatedOrdemServicos.value = filteredOrdemServicos.value.slice(startIndex, endIndex);
}

let totalPages = computed(() => Math.ceil(OrdemServicos.value.length / itemsPerPage.value));

onMounted(() => {
    listOrdemServicos();
})

watch(editDialog, clearUrlParam)

watch(filterInput, filterOrdemServicos)

watch(page, (newPage) => {
    paginate();
});

const changePage = (pageNumber: any) => {
    page.value = pageNumber;
};


const logout = () => {
  window.location.href = '/';
};

</script>
