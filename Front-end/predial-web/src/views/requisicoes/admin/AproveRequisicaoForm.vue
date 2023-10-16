<template>
    <div class="form-title">
      <h2>Requisição</h2>
    </div>
    <form action="" class="add-form">
    <div class="input-inline-field">
      <InputField 
        label="nome"
        placeholder="Informe o nome"
        v-model="nome_ordemValue"
      ></InputField>
      <InputField 
        label="Status da Requisição" 
        placeholder="Informe o status"
        v-model="statusRValue"
      ></InputField>
    </div>
    <div class="input-inline-field">
      <InputField 
        label="Data Abertura" 
        placeholder="Data da Abertura do chamado"
        v-model="dataaberturaValue"
      ></InputField>
      <InputField 
        label="Descrição" 
        placeholder="Descrição"
        v-model="descricaoValue"
      ></InputField>
    </div>
    <div class="input-inline-field">
      <InputField 
        label="CNPJ" 
        placeholder="CNPJ"
        v-model="cnpjValue"
      ></InputField>
      <InputField 
        label="Status da Ordem de Serviço" 
        placeholder="Informe status da Ordem de Serviço"
        v-model="status_ordemValue"
      ></InputField>
    </div>
    <div class="input-inline-field">
      <InputField 
        label="Inspeção" 
        placeholder="Inspeção"
        v-model="inspecaoValue"
      ></InputField>
      <InputField 
        label="Responsável" 
        placeholder="Informe Responsável"
        v-model="responsavelValue"
      ></InputField>
    </div>
    <div class="input-inline-field">
      <InputField 
        type="datetime-local"
        label="Data da prestação de Serviço" 
        placeholder="Informe a data"
        v-model="dataValue"
      ></InputField>
      <InputField 
        label="Checklist" 
        placeholder="Informe Checklist"
        v-model="checklValue"
      ></InputField>
    </div>
    </form>
  </template>
  
<script setup lang="ts">
  import InputField from '@/components/InputField/InputField.vue';
  import { onMounted, ref } from 'vue';
  import axios from 'axios';
  import router from '@/router';
  import type IRequisition from '../IRequisition';
    import type { Router } from 'vue-router';
  
  const nome_ordemValue = ref('');
  const statusRValue = ref('');
  const dataaberturaValue = ref('');
  const descricaoValue = ref('');
  const cnpjValue = ref('');
  const status_ordemValue = ref('');
  const dataValue = ref('');
  const responsavelValue = ref('');
  const checklValue = ref('');
  const inspecaoValue = ref('');

  const requisicaoSelected = ref<IRequisition>()

  onMounted(async () => {
    await waitForIdInRoute(router);
        const id = router.currentRoute.value.query.id;
        try {
            axios.get<IRequisition>(`http://localhost:8080/requisicao/${id}`).then((response) => {
            if(response.data){
                requisicaoSelected.value = response.data
                nome_ordemValue.value = requisicaoSelected.value.nome
                dataaberturaValue.value = requisicaoSelected.value.data_abertura
                statusRValue.value = requisicaoSelected.value.status
                descricaoValue.value = requisicaoSelected.value.descricao
                cnpjValue.value = requisicaoSelected.value.fk_cliente_id.cnpj
                inspecaoValue.value = requisicaoSelected.value.inspecao
            }
            console.log(response.data)
            })
        } catch (error) {
            console.log(error)
        }
  });
  
  async function waitForIdInRoute(router: Router) {
    while (router.currentRoute.value.query.id === undefined) {
      await new Promise((resolve) => setTimeout(resolve, 100));
    }
  }
  </script>
  