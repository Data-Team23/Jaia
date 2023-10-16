<template>
    <div class="container">
        <div class="container-top">

        </div>
        <div class="list-view">
            <div class="add-client-button-container">
                <div class="add-client-button">
                    <button @click="addDialog = true">
                        Adicionar cliente
                    </button>
                </div>
            </div>
            <div class="list-container">
                <div class="top-list">
                    <h3><strong>Carteira de clientes</strong></h3>
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
                                <th>Nome</th>
                                <th>Logradouro</th>
                                <th>Cnpj</th>
                                <th>Telefone</th>
                                <th>E-mail</th>
                                <th></th>
                            </tr>
                        </thead>
                    <tbody v-for="(cliente, index) in paginatedClients" :key="index">
                    <tr>
                    <td>{{ cliente.no }}</td>
                    <td>{{ cliente.nome }}</td>
                    <td>{{ cliente.endereco ?? 'Não informado' }}</td>
                    <td>{{ cliente.cnpj }}</td>
                    <td>{{ cliente.telefone }}</td>
                    <td>{{ cliente.email }}</td>
                    <td>
                    <span class="material-symbols-outlined" id="edit-button" @click="editClient(cliente.cnpj)">
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
                <AddClientForm></AddClientForm>
            </div>
        </v-dialog>
        <v-dialog v-model="editDialog" width="80%">
            <div class="add-client-container">
                <div class="close-button">
                    <span class="material-symbols-outlined" @click="editDialog = false">
                        close
                    </span>
                </div>
                <EditClientForm></EditClientForm>
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
import { ref, onMounted, type Ref, watch, computed, watchEffect } from 'vue';
import axios from 'axios';
import AddClientForm from "./AddClientForm.vue";
import EditClientForm from "./EditClientForm.vue";
import InputButton from '@/components/Button/InputButton.vue';
import SelectField from '@/components/Select/SelectField.vue';
import '../styles/form-styles.css'
import '../styles/table-styles.css'
import '../styles/dialog-styles.css'
import type IClient from './IClient'
import { globalCnpj } from './GlobalCnpj'; 
import { useRouter } from 'vue-router';

const router = useRouter()

let addDialog = ref(false)
let editDialog = ref(false)
let deleteDialog = ref(false)

let clients = ref<Array<IClient>>([])
let clientSelected = ref<IClient>()
let paginatedClients = ref<Array<IClient>>([]);
let filteredClients = ref<Array<IClient>>([])
let filterInput = ref("")
let selectedFilter = ref("nome")

console.log(paginatedClients.value)

function editClient(clientCnpj: string) {
    router.push({query: { cnpj: clientCnpj }})
    editDialog.value = true
}

function clearUrlParam(newValue: boolean) {
  if (!newValue && router.currentRoute.value.query.cnpj !== undefined) {
    router.push({ query: { ...router.currentRoute.value.query, cnpj: undefined } });
  }
}

const filterSelectOptions = [
    {
        label: "Nome",
        value: "nome"
    },
    {
        label: "Cnpj",
        value: "cnpj"
    },
    {
        label: "Telefone",
        value: "telefone"
    },
]

const page = ref(1);
const itemsPerPage = ref(5);

function listClients() {
    axios.get<any>('http://localhost:8080/cliente') 
        .then((response: any) => {
            clients.value = response.data
            filteredClients.value = response.data;
            clients.value.forEach((client, index) => {
                client.no = index + 1
            })
            filterClients();
        })
        .catch((error: any) => {
            console.error('Erro ao buscar clientes:', error);
        });
}

function filterClients() {  
    filteredClients.value = clients.value.filter((client: any) => {
        const selectedValue = client[selectedFilter.value];
        totalPages = computed(() => Math.ceil(filteredClients.value.length / itemsPerPage.value));
        if(selectedValue){
            return selectedValue.toLowerCase().includes(filterInput.value.toLowerCase());
        } else {
            return clients.value
        }
    })
    paginate()
}

const paginate = () => {
    const startIndex = (page.value - 1) * itemsPerPage.value;
    const endIndex = startIndex + itemsPerPage.value;

    paginatedClients.value = filteredClients.value.slice(startIndex, endIndex);
}

let totalPages = computed(() => Math.ceil(clients.value.length / itemsPerPage.value));

onMounted(() => {
    listClients();
})

watch(filterInput, filterClients)

watch(page, (newPage) => {
    paginate();
});

watch(editDialog, clearUrlParam)

const changePage = (pageNumber: any) => {
    page.value = pageNumber;
};
</script>