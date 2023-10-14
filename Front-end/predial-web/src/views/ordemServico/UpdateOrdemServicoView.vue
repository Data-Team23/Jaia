<template>
    <div class="form-title">
      <h2>Atualizar Ordem de Serviço</h2>
    </div>
    <form action="" class="add-form">
      <div class="input-inline-field">
        <InputField 
          label="Nome" 
          placeholder="Informe o nome"
          v-model="nome_ordemValue">
        </InputField>
        <InputField 
          label="Data Abertura" 
          placeholder="Data da Abertura do chamado"
          v-model="dataaberturaValue">
        </InputField>
      </div>
      <div class="input-inline-field">
        <InputField 
          label="CNPJ" 
          placeholder="CNPJ"
          v-model="cnpjValue">
        </InputField>
        <InputField 
          label="Inspeção" 
          placeholder="Inspeção"
          v-model="inspecaoValue">
        </InputField>
      </div>
      <div class="input-inline-field">
        <InputField 
          label="Status da Ordem de Serviço" 
          placeholder="Informe status da Ordem de Serviço"
          v-model=" status_ordemValue">
        </InputField>
        <InputField 
          label="Descrição" 
          placeholder="Informe a Descrição"
          v-model=" descricaoValue">
        </InputField>
      </div>
      <div class="input-inline-field">
        <InputField 
          label="Data da prestação de Serviço" 
          placeholder="Informe a data"
          v-model="dataValue">
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
  
  const nome_ordemValue = ref('');
  const dataaberturaValue = ref('');
  const cnpjValue = ref('');
  const inspecaoValue = ref('');
  const status_ordemValue = ref('');
  const descricaoValue = ref('');
  const dataValue = ref('');
  
  const ordem_servicoSelected = ref<IOrdemServico>()
  
  onMounted(async () => {
    await waitForIdInRoute(router);
    const id = router.currentRoute.value.query.id;
    try {
      axios
        .get<IOrdemServico>(`http://localhost:8080/ordem-servico/${id}`)
        .then((response) => {
          if (response.data) {
            ordem_servicoSelected.value = response.data;
            console.log(ordem_servicoSelected.value)
            nome_ordemValue.value = ordem_servicoSelected.value.nome_ordem;
            dataaberturaValue.value = ordem_servicoSelected.value.data_abertura;
            cnpjValue.value = ordem_servicoSelected.value.cnpj;
            inspecaoValue.value = ordem_servicoSelected.value.inspecao;
            status_ordemValue.value = ordem_servicoSelected.value.status_ordem;
            descricaoValue.value = ordem_servicoSelected.value.descricao;
            dataValue.value = ordem_servicoSelected.value.data;
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

  
async function updateOS() {
  event?.preventDefault()
  try {
    const id = router.currentRoute.value.query.cnpj;
    const ordemservicoAtualizado = {
      nome: nome_ordemValue.value,   
      data_abertura: dataaberturaValue.value,
      cnpj: cnpjValue.value,
      inspecao: inspecaoValue.value,
      status: status_ordemValue.value,
      descricao: descricaoValue.value,
      data: dataValue.value, 
    };

    await axios.put(`http://localhost:8080/ordem-servico/${id}`, ordemservicoAtualizado).then((response) => {
      window.alert("Cliente atualizado com sucesso")
      console.log(response)
      location.reload()
    }).catch((error) => {
      console.log(error)
    })

  } catch (error) {
    console.error('Erro interno ao atualizar o cliente', error);
  }
}

  </script>
  