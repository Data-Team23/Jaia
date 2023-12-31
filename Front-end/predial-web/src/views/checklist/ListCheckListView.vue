<template>
    <div class="container">
        <div class="container-top">

        </div>
        <div class="list-view">
            <div class="add-client-button-container">
                <div class="add-client-button">
                    <button @click="addDialog = true">
                        Adicionar checklist
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
                                <th>Departamento</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody v-for="(checkList, index) in paginatedChecklist" :key="index">
                            <tr>
                                <td>{{ checkList.no }}</td>
                                <td>{{ checkList.nome }}</td>
                                <td>
                                    <span v-if="checkList.departamento">{{ checkList.departamento.nome
                                    }}</span>
                                    <span v-else>Não informado</span>
                                </td>
                                <td>
                                    <span class="material-symbols-outlined" id="edit-button"
                                        @click="editCheckList(checkList.id.toString())">
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
                <AddCheckListView></AddCheckListView>
            </div>
        </v-dialog>
        <v-dialog v-model="editDialog" width="80%">
            <div class="add-client-container">
                <div class="close-button">
                    <span class="material-symbols-outlined" @click="editDialog = false">
                        close
                    </span>
                </div>
                <UpdateCheckListView></UpdateCheckListView>
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

import axios from 'axios';

import { ref, onMounted, watch, computed } from 'vue';
import { useRouter } from 'vue-router';

import type ICheckList from './ICheckList';
import UpdateCheckListView from './UpdateCheckListView.vue';
import AddCheckListView from './AddCheckListView.vue';

import InputButton from '@/components/Button/InputButton.vue';
import SelectField from '@/components/Select/SelectField.vue';

const router = useRouter()

let addDialog = ref(false)
let editDialog = ref(false)
let deleteDialog = ref(false)

const page = ref(1);
const itemsPerPage = ref(5);

const apiUrl = import.meta.env.VITE_API_URL

let checkLists = ref<Array<ICheckList>>([])
let filteredChecklist = ref<Array<ICheckList>>([])
let paginatedChecklist = ref<Array<ICheckList>>([])
let selectedFilter = ref("")
let filterInput = ref("")

function editCheckList(id: string) {
    router.push({ query: { id: id } })
    editDialog.value = true
}

function clearUrlParam(newValue: boolean) {
    if (!newValue && router.currentRoute.value.query.id !== undefined) {
        router.push({ query: { ...router.currentRoute.value.query, id: undefined } });
    }
}

watch(editDialog, clearUrlParam)

const filterSelectOptions = [
  {
    label: "Nome",
    value: "nome"
  },
  {
    label: "Código",
    value: "idDepart"
  }
];

function listCheckList() {
    axios.get<any>(`http://localhost:8080/checklist`).then((response: any) => {
        console.log(response.data)
        checkLists.value = response.data
        filteredChecklist = response.data
        checkLists.value.forEach((checklist, index) => {
            checklist.no = index + 1
        })
        filterChecklist()
    })
}

function filterChecklist() {
    filteredChecklist.value = checkLists.value.filter((client: any) => {
        const selectedValue = client[selectedFilter.value];
        totalPages = computed(() => Math.ceil(filteredChecklist.value.length / itemsPerPage.value));
        console.log(filterInput.value)
        if (selectedValue) {
            return selectedValue.toLowerCase().includes(filterInput.value.toLowerCase());
        } else {
            return checkLists.value
        }
    })
    paginate()
}

const paginate = () => {
    const startIndex = (page.value - 1) * itemsPerPage.value;
    const endIndex = startIndex + itemsPerPage.value;

    paginatedChecklist.value = filteredChecklist.value.slice(startIndex, endIndex);
}

let totalPages = computed(() => Math.ceil(checkLists.value.length / itemsPerPage.value));

onMounted(() => {
    listCheckList()
})

watch(filterInput, filterChecklist)

watch(page, (newPage) => {
    paginate();
});

const changePage = (pageNumber: any) => {
    page.value = pageNumber;
};

</script>