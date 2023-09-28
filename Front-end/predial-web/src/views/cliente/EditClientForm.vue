<template>
    <div class="form-title">
        <h2>Atualizar cliente</h2>
    </div>
    <form action="" class="add-form">
        <div class="input-inline-field">
            <InputField 
                label="CNPJ:" 
                value="00.000.000/0000-01"
                :model-value="cnpjValue">
            </InputField>
            <InputField 
                label="Telefone:" 
                placeholder="Informe o telefone:"
                :model-value="phoneValue">
            </InputField>
        </div>
        <div class="input-inline-field">
            <InputField 
                label="Nome:" 
                placeholder="Informe o nome:"
                :model-value="nameValue">
            </InputField>
            <InputField 
                label="E-mail:" 
                placeholder="Informe o e-mail:"
                :model-value="emailValue">
            </InputField>
        </div>
        <div class="input-inline-field">
            <InputField 
                label="Endereço:" 
                placeholder="Informe o endereço:"
                :model-value="addressValue">
            </InputField>
        </div>
        <div class="send-button">
            <InputButton text-button="Salvar" @click="updateClient"></InputButton>
        </div>
    </form>
</template>

<script setup lang="ts">
import InputField from '@/components/InputField/InputField.vue';
import InputButton from '@/components/Button/InputButton.vue';
import { globalCnpj } from './GlobalCnpj'; 
import { ref } from 'vue';
import axios from 'axios';
import { useRoute } from 'vue-router';

const route = useRoute()
const cnpjValue = ref('');
const phoneValue = ref('');
const nameValue = ref('');
const emailValue = ref('');
const addressValue = ref('');
const mensagemSucesso = ref('');

async function updateClient() {
  event?.preventDefault()
  try {
    const clienteCnpj = route.query.cnpj
    console.log(route.query.cnpj)
    const clienteAtualizado = {
      nome: nameValue.value, 
      telefone: parseFloat(phoneValue.value),
      senha: null,
      email: emailValue.value, 
      endereco: addressValue.value, 
    };
    console.log('Cliente Atualizado:', clienteAtualizado);


<<<<<<< HEAD
    const response = await axios.put(`http://localhost:8080/cliente/atualizar/"${clienteCnpj}"`, clienteAtualizado);
=======
    const response = await axios.put(`http://localhost:8080/cliente/atualizar/${clienteCnpj}`, clienteAtualizado);
>>>>>>> 07d327fcfd4bdacd76fcb32d08d94bf9c2dba021

    if (response.status === 200) {
      mensagemSucesso.value = 'Cliente atualizado com sucesso'; 
    } else {
      console.error('Erro ao atualizar o cliente');
    }
  } catch (error) {
    console.error('Erro interno ao atualizar o cliente', error);
  }
}

<<<<<<< HEAD
</script>
=======
</script>

>>>>>>> 07d327fcfd4bdacd76fcb32d08d94bf9c2dba021
