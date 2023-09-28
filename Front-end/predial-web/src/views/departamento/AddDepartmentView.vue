<template>
  <div class="form-title">
    <h2>Adicionar departamento</h2>
  </div>
  <form action="" class="add-form">
    <div class="input-inline-field">
      <InputField 
        label="Nome:" 
        placeholder="Informe o nome"
        v-model="nameValue">
      </InputField>
      <InputField 
        label="Código:" 
        placeholder="Informe o código"
        v-model="codDepartValue">
      </InputField>
    </div>
    <div class="send-button">
      <InputButton text-button="Salvar" @click="createDepartment()"></InputButton>
    </div>
  </form>
</template>

<script setup lang="ts">
import InputField from '@/components/InputField/InputField.vue';
import InputButton from '@/components/Button/InputButton.vue';
import { ref } from 'vue';
import axios from 'axios';

const codDepartValue = ref("");
const nameValue = ref("");

async function createDepartment() {
  event?.preventDefault();

  const departamento = {
    codDepart: parseInt(codDepartValue.value), 
    nome: nameValue.value,
  };

  try {
    const response = await axios.post('http://localhost:8080/departamentos', departamento);
    codDepartValue.value = "";
    nameValue.value = "";
    console.log(response)
    window.alert("Departamento criado com sucesso")
    location.reload()
  } catch (error) {
    console.error('Erro ao criar departamento:', error);
    window.alert("Erro ao criar departamento")
  }
}

</script>
