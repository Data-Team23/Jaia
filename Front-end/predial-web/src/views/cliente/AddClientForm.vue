<template>
  <div class="form-title">
    <h2>Adicionar cliente</h2>
  </div>
  <form action="" class="add-form">
    <div class="input-inline-field">
      <InputField label="CNPJ:" placeholder="00.000.000/0000-01" v-model="cnpjValue" />
      <InputField label="Telefone:" placeholder="(12) 996534789" v-model="phoneValue" />
    </div>
    <div class="input-inline-field">
      <InputField label="Nome:" placeholder="Informe o nome" v-model="nameValue" />
      <InputField label="E-mail:" placeholder="Informe o email" v-model="emailValue" />
    </div>
    <div class="input-inline-field">
      <InputField label="Endereço:" placeholder="Rua Itajaí n° 435" v-model="addressValue" />
    </div>
    <div class="send-button">
      <InputButton text-button="Salvar" @click="createClient" />
    </div>
  </form>
  <div v-if="successMessage" class="success-message">
    {{ successMessage }}
  </div>
</template>

<script setup lang="ts">
import InputButton from '@/components/Button/InputButton.vue';
import InputField from '@/components/InputField/InputField.vue';
import axios from 'axios';
import { ref } from 'vue';

const cnpjValue = ref("");
const phoneValue = ref("");
const nameValue = ref("");
const emailValue = ref("");
const addressValue = ref("");
const successMessage = ref(""); 

async function sendEmail() {

  const clientEmail = {
    cnpj: cnpjValue.value,
    nome: nameValue.value,
    email: emailValue.value,
    endereco: addressValue.value,
  }
  console.log('Sending email with data:', clientEmail);

  try {
    const response = await axios.post('http://localhost:8080/email/enviar', clientEmail);
    window.alert("Email enviado com sucesso");
  } catch (error) {
    console.error('Erro ao enviar E-mail:', error);
  }
}

async function createClient() {
  event?.preventDefault();

  const client = {
    cnpj: cnpjValue.value, 
    nome: nameValue.value,
    email: emailValue.value,
    endereco: addressValue.value,
    telefone: phoneValue.value,
    senha: '123'
  };

  try {
    const response = await axios.post('http://localhost:8080/cliente', client);
    window.alert("Cliente criado com sucesso");
    sendEmail();
    window.alert("Email enviado com sucesso");
    cnpjValue.value = "";
    phoneValue.value = "";
    nameValue.value = "";
    emailValue.value = "";
    addressValue.value = "";
  } catch (error) {
    console.error('Erro ao criar cliente:', error);
    window.alert("Erro ao criar cliente");
    successMessage.value = "";
  }
}
</script>
