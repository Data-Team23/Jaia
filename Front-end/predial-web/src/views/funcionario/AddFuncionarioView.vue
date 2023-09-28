<template>
  <div class="form-title">
    <h2>Adicionar funcionário</h2>
  </div>
  <form action="" class="add-form">
    <div class="input-inline-field">
      <InputField 
        label="Nome" 
        placeholder="Informe o nome"
        v-model="nomeValue">
      </InputField>
      <InputField 
        label="E-mail" 
        placeholder="Informe o e-mail"
        v-model="emailValue">
      </InputField>
    </div>
    <div class="input-inline-field">
      <InputField 
        label="CPF" 
        placeholder="Informe o CPF"
        v-model="cpfValue">
      </InputField>
      <InputField 
        label="Telefone" 
        placeholder="Informe o telefone"
        v-model="telefoneValue">
      </InputField>
    </div>
    <div class="input-inline-field">
      <InputField 
        label="Senha" 
        placeholder="Informe a senha"
        v-model="senhaValue"
        type="password">
      </InputField>
      <InputField 
        label="Informe o departamento" 
        placeholder="Informe o código departamento"
        v-model="departamentoValue">
      </InputField>
    </div>
    <div class="send-button">
      <InputButton text-button="Salvar" @click="createFuncionario"></InputButton>
    </div>
  </form>
</template>

<script setup lang="ts">
import InputField from '@/components/InputField/InputField.vue';
import InputButton from '@/components/Button/InputButton.vue';
import { ref } from 'vue';
import axios from 'axios';

const nomeValue = ref('');
const emailValue = ref('');
const cpfValue = ref('');
const telefoneValue = ref('');
const senhaValue = ref('');
const departamentoValue = ref('');

async function createFuncionario() {
  event?.preventDefault();

  const funcionario = {
    cpf: cpfValue.value, 
    nome: nomeValue.value,
    email: emailValue.value,
    senha: senhaValue.value,
    telefone: telefoneValue.value
  };

  try {
    const response = await axios.post('http://localhost:8080/funcionario', funcionario);
    cpfValue.value = "";
    nomeValue.value = "";
    emailValue.value = "";
    senhaValue.value = "";
    telefoneValue.value = "";
    console.log(response)
    window.alert("Funcionario criado com sucesso")
    location.reload()
  } catch (error) {
    console.error('Erro ao criar Funcionario:', error);
    window.alert("Erro ao criar Funcionario")
  }
}


</script>
