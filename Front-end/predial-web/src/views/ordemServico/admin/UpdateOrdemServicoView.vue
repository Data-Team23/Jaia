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
      <div class="send-button">
        <InputButton text-button="Salvar" @click="updateOrdemServico"></InputButton>
      </div>
    </form>
  </template>
  
  <script setup lang="ts">
  import InputField from '@/components/InputField/InputField.vue';
  import InputButton from '@/components/Button/InputButton.vue';
  import { onMounted, ref } from 'vue';
  import { useRouter, type Router } from 'vue-router';
  import type IOrdemServico from '../IOrdemServico';
  import axios from 'axios';
  
  const router = useRouter()
  
  const nome_ordemValue = ref('');
  const dataaberturaValue = ref('');
  const cnpjValue = ref('');
  const inspecaoValue = ref('');
  const status_ordemValue = ref('');
  const descricaoValue = ref('');
  
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
            dataaberturaValue.value = ordem_servicoSelected.value.id_req.data_abertura;
            cnpjValue.value = ordem_servicoSelected.value.id_req.fk_cliente_id.cnpj;
            inspecaoValue.value = ordem_servicoSelected.value.tipo_inspecao;
            status_ordemValue.value = ordem_servicoSelected.value.status_ordem;
            descricaoValue.value = ordem_servicoSelected.value.id_req.descricao;
          }ordem_servicoSelected
        });
    } catch (error) {
      console.log(error);
    }
  });
  
  async function waitForIdInRoute(router: Router) {
    while (router.currentRoute.value.query.id === undefined) {
      await new Promise((resolve) => setTimeout(resolve, 100));
    }
  }

  async function updateOrdemServico() {
  event?.preventDefault()
  try {
    const id = router.currentRoute.value.query.id;
    const ordemServicoAtualizada = {
      nome_ordem: nome_ordemValue.value, 
      tipo_inspecao: inspecaoValue.value,
      status_ordem: status_ordemValue.value, 
    };

    await axios.put(`http://localhost:8080/ordem-servico/${id}`, ordemServicoAtualizada).then((response) => {
      window.alert("Ordem de serviço atualizado com sucesso")
      console.log(response)
      location.reload()
    }).catch((error) => {
      console.log(error)
    })

  } catch (error) {
    console.error('Erro interno ao atualizar o ordem de serviço', error);
  }
}

  </script>
  