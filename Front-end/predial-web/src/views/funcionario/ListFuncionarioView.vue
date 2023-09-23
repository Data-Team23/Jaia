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
                <th>CPF</th>
                <th>Departamento</th>
                <th>E-mail</th>
                <th></th>
              </tr>
            </thead>
            <tbody v-for="(funcionario, index) in funcionarios" :key="index">
              <tr>  
                <td>{{ index + 1 }}</td>
                <td>{{ funcionario.name }}</td>
                <td>{{ funcionario.cpf }}</td>
                <td>{{ funcionario.departamento }}</td>
                <td>{{ funcionario.email }}</td>
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
        <AddFuncionarioForm></AddFuncionarioForm>
      </div>
    </v-dialog>
    <v-dialog v-model="editDialog" width="80%">
      <div class="add-client-container">
        <div class="close-button">
          <span class="material-symbols-outlined" @click="editDialog = false"> close </span>
        </div>
        <EditFuncionarioForm></EditFuncionarioForm>
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
import AddFuncionarioForm from "./AddFuncionarioView.vue";
import EditFuncionarioForm from "./UpdateFuncionarioView.vue";
import InputButton from '@/components/Button/InputButton.vue';

let addDialog = ref(false);
let editDialog = ref(false);
let deleteDialog = ref(false);

const funcionarios = [
  {
    code: '001',
    name: 'Vitoria',
    cpf: '123.456.789-00',
    departamento: 'RH',
    email: 'vitoria@example.com',
  },
  {
    code: '002',
    name: 'Julia',
    cpf: '987.654.321-00',
    departamento: 'Financeiro',
    email: 'julia@example.com',
  },
  {
    code: '003',
    name: 'Lucas',
    cpf: '111.222.333-00',
    departamento: 'Tecnologia',
    email: 'lucas@example.com',
  },
  {
    code: '004',
    name: 'Tobias',
    cpf: '444.555.666-00',
    departamento: 'Vendas',
    email: 'tobias@example.com',
  },
  {
    code: '005',
    name: 'Ariane',
    cpf: '999.888.777-00',
    departamento: 'Marketing',
    email: 'ariane@example.com',
  },
];
</script>
