<template>
  <form action="" class="add-form">
    <div class="input-inline-field">
      <InputField 
        label="Nome:" placeholder="Informe o nome" :model-value="nameValue">
      </InputField>
      <InputField 
        label="Responsável:" placeholder="Informe o responsável" :model-value="responsibleValue">
      </InputField>
    </div>
    <div class="input-inline-field">
      <InputField label="Código:" placeholder="Informe o código" :model-value="codeValue">
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

const codeValue = ref('');
const responsibleValue = ref('');
const nameValue = ref('');
</script>
