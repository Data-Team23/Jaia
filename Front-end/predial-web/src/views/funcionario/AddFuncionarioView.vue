<template>
  <form action="" class="add-form">
    <div class="input-inline-field">
      <InputField 
        label="Nome" 
        placeholder="Informe o nome"
        :model-value="nomeValue">
      </InputField>
      <InputField 
        label="E-mail" 
        placeholder="Informe o e-mail"
        :model-value="emailValue">
      </InputField>
    </div>
    <div class="input-inline-field">
      <InputField 
        label="CPF" 
        placeholder="Informe o CPF"
        :model-value="cpfValue">
      </InputField>
      <InputField 
        label="Departamento" 
        placeholder="Informe o departamento"
        :model-value="departamentoValue">
      </InputField>
    </div>
    <div class="send-button">
      <InputButton text-button="Salvar" @click="salvar"></InputButton>
    </div>
  </form>
</template>

<script setup lang="ts">
import InputField from '@/components/InputField/InputField.vue';
import InputButton from '@/components/Button/InputButton.vue';
import { ref } from 'vue';

const nomeValue = ref('');
const emailValue = ref('');
const cpfValue = ref('');
const departamentoValue = ref('');

const salvar = () => {
  const nome = nomeValue.value;
  const email = emailValue.value;
  const cpf = cpfValue.value;
  const departamento = departamentoValue.value;

  console.log("Nome:", nome);
  console.log("E-mail:", email);
  console.log("CPF:", cpf);
  console.log("Departamento:", departamento);
}
</script>
