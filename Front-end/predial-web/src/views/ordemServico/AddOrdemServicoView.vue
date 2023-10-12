<template>
    <div class="form-title">
      <h2>Cadastrar Ordem de Serviço</h2>
    </div>
    <form action="" class="add-form">
      <div class="input-inline-field">
        <InputField 
          label="Nome" 
          placeholder="Informe o nome"
          v-model="nomeValue">
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
          v-model=" statusValue">
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
      <div class="send-button">
        <InputButton text-button="Salvar" @click="createOrdemServico"></InputButton>
      </div>
    </form>
  </template>
  
  <script setup lang="ts">
  import InputField from '@/components/InputField/InputField.vue';
  import InputButton from '@/components/Button/InputButton.vue';
  import { ref } from 'vue';
  import axios from 'axios';
  
  const nomeValue = ref('');
  const statusRValue = ref('');
  const DataAberturaValue = ref('');
  const descricaoValue = ref('');
  const cnpjValue = ref('');
  const statusValue = ref('');
  const inspecaoValue = ref('');
  const responsavelValue = ref('');
  const dataValue = ref('');
  const checklValue = ref('');
  
  async function createOrdemServico() {
    event?.preventDefault();
  
    const ordem_servico = {
      responsavel: responsavelValue.value,
      status: statusValue.value
    };
  
    try {
      const response = await axios.post('http://localhost:8080/ordem_servico', ordem_servico);

      responsavelValue.value = "";
      statusValue.value = "";
      console.log(response)
      window.alert("Funcionario criado com sucesso")
      location.reload()
    } catch (error) {
      console.error('Erro ao criar Funcionario:', error);
      window.alert("Erro ao criar Funcionario")
    }
  }
  
  
  </script>
  