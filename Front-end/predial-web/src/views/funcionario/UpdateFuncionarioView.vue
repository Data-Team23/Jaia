<template>
  <form action="" class="add-form">
    <div class="input-inline-field">
      <InputField 
        label="Nome:" 
        placeholder="Informe o nome" 
        :model-value="nameValue">
      </InputField>
      <InputField 
        label="E-mail:" 
        placeholder="Informe o e-mail" 
        :model-value="emailValue">
      </InputField>
    </div>
    <div class="input-inline-field">
      <InputField 
      
        label="CPF:" 
        placeholder="Informe o CPF" 
        :model-value="cpfValue">
      </InputField>
      <InputField 
        label="Departamento:" 
        placeholder="Informe o departamento" 
        :model-value="departmentValue">
      </InputField>
      
    </div>
    <div class="send-button">
      <InputButton text-button="Salvar"></InputButton>
    </div>
  </form>
</template>

<script setup lang="ts">
import InputField from '@/components/InputField/InputField.vue';
import InputButton from '@/components/Button/InputButton.vue';
import { ref } from 'vue';

const cpfValue = ref('');
const departmentValue = ref('');
const emailValue = ref('');
const nameValue = ref('');
</script>
