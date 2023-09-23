<template>
  <div class="container">
    <div class="container-top"></div>
    <div class="list-view">
      <div class="add-client-button-container">
        <div class="add-client-button">
          <button @click="addDialog = true">Adicionar departamento</button>
        </div>
      </div>
      <div class="list-container">
        <div class="top-list">
          <h3><strong>Carteira de departamentos</strong></h3>
          <div class="search-input">
            <input type="text" />
          </div>
        </div>
        <div class="table-container">
          <table>
            <thead>
              <tr>
                <th>No.</th>
                <th>Código</th>
                <th>Responsável</th>
                <th></th>
              </tr>
            </thead>
            <tbody v-for="(department, index) in departments" :key="index">
              <tr>  
                <td>{{ index + 1 }}</td>
                <td>{{ department.code }}</td>
                <td>{{ department.responsible }}</td>
                <td>
                  <span class="material-symbols-outlined" id="edit-button" @click="editDialog = true"> edit </span>
                  <span class="material-symbols-outlined" id="delete-button" @click="deleteDialog = true"> delete </span>
                </td>
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
        <AddDepartmentForm></AddDepartmentForm>
      </div>
    </v-dialog>
    <v-dialog v-model="editDialog" width="80%">
      <div class="add-client-container">
        <div class="close-button">
          <span class="material-symbols-outlined" @click="editDialog = false"> close </span>
        </div>
        <EditDepartmentForm></EditDepartmentForm>
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
import { ref } from 'vue';
import "./styles.css";
import AddDepartmentForm from "./AddDepartmentView.vue";
import EditDepartmentForm from "./UpdateDepartmentView.vue";
import InputButton from '@/components/Button/InputButton.vue';

let addDialog = ref(false);
let editDialog = ref(false);
let deleteDialog = ref(false);

const departments = [
  {
    code: '001',
    responsible: 'Vitoria',
  },
  {
    code: '002',
    responsible: 'Julia',
  },
  {
    code: '003',
    responsible: 'Lucas',
  },
  {
    code: '004',
    responsible: 'Tobias',
  },
  {
    code: '005',
    responsible: 'Ariane',
  },
];
</script>
