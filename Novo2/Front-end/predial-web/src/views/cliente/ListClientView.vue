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
                    <div class="search-input">
                        <input type="text">
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
                                <th></th>
                            </tr>
                        </thead>
                        <tbody v-for="(cliente, index) in clientes" :key="index">
                            <tr>
                                <td>{{ index+1 }}</td>
                                <td>{{ cliente.nome }}</td>
                                <td>{{ cliente.logradouro }}</td>
                                <td>{{ cliente.cnpj }}</td>
                                <td>{{ cliente.telefone }}</td>
                                <td>
                                    <span class="material-symbols-outlined" id="edit-button" @click="editDialog = true">
                                        edit
                                    </span>
                                    <span class="material-symbols-outlined" id="delete-button" @click="deleteDialog = true">
                                        delete
                                    </span>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <!-- <v-pagination
                        v-model="page"
                        :length="clientes.length / 5"
                        rounded="circle"
                    ></v-pagination> -->
                </div>
            </div>
        </div>
        <v-dialog
            v-model="addDialog"
            width="80%"
        >
            <div class="add-client-container">
                <div class="close-button">
                    <span class="material-symbols-outlined" @click="addDialog = false">
                        close
                    </span>
                </div>
                <AddClientForm></AddClientForm>
            </div>
        </v-dialog>
        <v-dialog
            v-model="editDialog"
            width="80%"
        >
            <div class="add-client-container">
                <div class="close-button">
                    <span class="material-symbols-outlined" @click="editDialog = false">
                        close
                    </span>
                </div>
                <EditClientForm></EditClientForm>
            </div>
        </v-dialog>
        <v-dialog
            v-model="deleteDialog" 
            width="30%"   
        >
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

    import { ref } from 'vue';
    import AddClientForm from "./AddClientForm.vue";
    import EditClientForm from "./EditClientForm.vue";
    import InputButton from '@/components/Button/InputButton.vue';
    import '../styles/form-styles.css'
    import '../styles/table-styles.css'
    import '../styles/dialog-styles.css'
    
    let addDialog = ref(false)
    let editDialog = ref(false)
    let deleteDialog = ref(false)
    

    const page = 1 

    const clientes = [
        {
            nome: 'Joao Carlos',
            logradouro: 'Rua Itajaí n° 245',
            cnpj: '09.888.344/0001-01',
            telefone: '(12) 9677-8854'
        },
        {
            nome: 'Joao Carlos',
            logradouro: 'Rua Itajaí n° 245',
            cnpj: '09.888.344/0001-01',
            telefone: '(12) 9677-8854'
        },
        {
            nome: 'Joao Carlos',
            logradouro: 'Rua Itajaí n° 245',
            cnpj: '09.888.344/0001-01',
            telefone: '(12) 9677-8854'
        },
        {
            nome: 'Joao Carlos',
            logradouro: 'Rua Itajaí n° 245',
            cnpj: '09.888.344/0001-01',
            telefone: '(12) 9677-8854'
        },
        {
            nome: 'Joao Carlos',
            logradouro: 'Rua Itajaí n° 245',
            cnpj: '09.888.344/0001-01',
            telefone: '(12) 9677-8854'
        }
    ]
</script>