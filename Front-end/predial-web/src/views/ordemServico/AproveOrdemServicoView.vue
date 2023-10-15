<template>
    <div class="form-title">
      <h2>Ordem de Serviço</h2>
    </div>
    <form action="" class="add-form">
        <div class="input-inline-field">
        <InputField 
          label="nome"
          placeholder="Informe o nome"
          v-model="nome_ordemValue">
        </InputField>
        <InputField 
          label="Status da Requisição" 
          placeholder="Informe o status"
          v-model="statusRValue">
        </InputField>
      </div>
      <div class="input-inline-field">
        <InputField 
          label="Data Abertura" 
          placeholder="Data da Abertura do chamado"
          v-model="DataAberturaValue">
        </InputField>
        <InputField 
          label="Descrição" 
          placeholder="Descrição"
          v-model="descricaoValue">
        </InputField>
      </div>
      <div class="input-inline-field">
        <InputField 
          label="CNPJ" 
          placeholder="CNPJ"
          v-model="cnpjValue">
        </InputField>
        <InputField 
          label="Status da Ordem de Serviço" 
          placeholder="Informe status da Ordem de Serviço"
          v-model=" status_ordemValue">
        </InputField>
      </div>
      <div class="input-inline-field">
        <InputField 
          label="Inspeção" 
          placeholder="Inspeção"
          v-model="inspecaoValue">
        </InputField>
        <InputField 
          label="Responsável" 
          placeholder="Informe Responsável"
          v-model=" responsavelValue">
        </InputField>
      </div>
      <div class="input-inline-field">
        <InputField 
          label="Data da prestação de Serviço" 
          placeholder="Informe a data"
          v-model="dataValue">
        </InputField>
        <InputField 
          label="Checklist" 
          placeholder="Informe Checklist"
          v-model=" checklValue">
        </InputField>
      </div>
      <div class="input-inline-field">
        <label for="showAdditionalFields">Mostrar campos adicionais</label>
        <input type="checkbox" id="showAdditionalFields" v-model="showAdditionalFields">
      </div>
      <div class="input-inline-field" v-if="showAdditionalFields">
        <InputField 
        label="Campo Adicional 1" 
        placeholder="Informe o campo adicional 1"
        v-model="campoAdicional1">
        </InputField>
        <InputField 
          label="Campo Adicional 2" 
          placeholder="Informe o campo adicional 2"
          v-model="campoAdicional2">
        </InputField>
      </div>
      <div class="aprove-button"> 
        <InputButton text-button="Aprovar"></InputButton>
      </div>
      <div class="disapprove-button">
        <InputButton text-button="Reprovar"></InputButton>
      </div>
    </form>
  </template>
  
  <script setup lang="ts">
  import InputField from '@/components/InputField/InputField.vue';
  import InputButton from '@/components/Button/InputButton.vue';
  import { onMounted, ref } from 'vue';
  import { useRouter, type Router } from 'vue-router';
  import type IOrdemServico from './IOrdemServico';
  import axios from 'axios';
  

  
  const nome_ordemValue = ref('');
  const statusRValue = ref('');
  const DataAberturaValue = ref('');
  const descricaoValue = ref('');
  const cnpjValue = ref('');
  const status_ordemValue = ref('');
  const inspecaoValue = ref('');
  const responsavelValue = ref('');
  const dataValue = ref('');
  const checklValue = ref('');
 
  const showAdditionalFields = ref(false);
  const campoAdicional1 = ref('');
  const campoAdicional2 = ref('');

  function listOrdemServicos() {
    axios.get<any>('http://localhost:8080/ordem-servico') 
         
}
  
  </script>
  