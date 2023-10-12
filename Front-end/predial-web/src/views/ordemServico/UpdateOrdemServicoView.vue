<template>
    <div class="form-title">
      <h2>Atualizar Ordem de Serviço</h2>
    </div>
    <form action="" class="add-form">
      <div class="input-inline-field">
        <InputField 
          label="Nome:" 
          placeholder="Informe o nome" 
          v-model="nameValue">
        </InputField>
        <InputField 
          label="E-mail:" 
          placeholder="Informe o e-mail" 
          v-model="emailValue">
        </InputField>
      </div>
      <div class="input-inline-field">
        <InputField    
          label="CPF:" 
          placeholder="Informe o CPF" 
          v-model="cpfValue">
        </InputField>    
        <InputField    
          label="Telefone:" 
          placeholder="Informe o telefone" 
          v-model="statusValue">
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
  import { onMounted, ref } from 'vue';
  import { useRouter, type Router } from 'vue-router';
  import type IOrdemServico from './IOrdemServico';
  import axios from 'axios';
  
  const router = useRouter()
  
  const cpfValue = ref('');
  const departmentValue = ref('');
  const emailValue = ref('');
  const nameValue = ref('');
  const senhaValue = ref('');
  const statusValue = ref('');
  
  const ordem_servicoSelected = ref<IOrdemServico>()
  
  onMounted(async () => {
    await waitForIdInRoute(router);
    const id = router.currentRoute.value.query.id;
    try {
      axios
        .get<IOrdemServico>(`http://localhost:8080/ordem_servico/${id}`)
        .then((response) => {
          if (response.data) {
            ordem_servicoSelected.value = response.data;
            console.log(ordem_servicoSelected.value)
            nameValue.value = ordem_servicoSelected.value.nome;
            statusValue.value = ordem_servicoSelected.value.status;
          }ordem_servicoSelected
        });
    } catch (error) {
      console.log(error);
    }
  });
  
  async function waitForIdInRoute(router: Router) {
    // Loop para aguardar até que route.query.cnpj esteja definido
    while (router.currentRoute.value.query.id === undefined) {
      // Definição do período de tempo (pode ser ajustado)
      await new Promise((resolve) => setTimeout(resolve, 100));
    }
  }
  </script>
  