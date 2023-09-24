<template>
  <div class="form-title">
    <h2>Adicionar departamento</h2>
  </div>
  <form action="" class="add-form">
    <div class="input-inline-field">
      <InputField 
        label="Nome:" 
        placeholder="Informe o nome">
      </InputField>
      <InputField 
        label="Responsável:" 
        placeholder="Informe o responsável">
      </InputField>
    </div>
    <div class="input-inline-field">
      <InputField 
        label="Código:" 
        placeholder="Informe o código">
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

</script>
