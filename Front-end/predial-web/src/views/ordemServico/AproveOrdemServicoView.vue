<template>
  <div class="form-title">
    <h2>Ordem de Serviço</h2>
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
    
    <div class="input-inline-field" v-for="(pergunta, index) in ordem_servicoSelected?.id_check?.perguntas || []" :key="index">
        <div class="column">
          <label class="input-inline-field">{{ pergunta.pergunta }}</label>
          <select class="input-select" v-model="pergunta.status">
            <option style="color: black;" value="Aprovado">Aprovado</option>
            <option value="Reprovado">Reprovado</option>
          </select>
          <InputField class="input-inline-field" style="width: 445px;"
            label="" 
            placeholder="Comentário"
            v-model="pergunta.comentario"
          ></InputField>
        </div>
      </div>
    
    <div class="send-button"> 
      <InputButton text-button="Aprovar"></InputButton>
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
import router from '@/router';

const nome_ordemValue = ref('');
const statusRValue = ref('');
const dataaberturaValue = ref('');
const descricaoValue = ref('');
const cnpjValue = ref('');
const status_ordemValue = ref('');
const inspecaoValue = ref('');
const responsavelValue = ref('');
const dataValue = ref('');
const checklValue = ref('');
const respostas = ref([]);
const ordem_servicoSelected = ref<IOrdemServico | null>(null);

onMounted(async () => {
  await waitForIdInRoute(router);
  const id = router.currentRoute.value.query.id;
  try {
    const response = await axios.get<IOrdemServico>(`http://localhost:8080/ordem-servico/${id}`);
    ordem_servicoSelected.value = response.data;

    ordem_servicoSelected.value = response.data;
            console.log(ordem_servicoSelected.value)
            nome_ordemValue.value = ordem_servicoSelected.value.nome_ordem;
            dataaberturaValue.value = ordem_servicoSelected.value.id_req.data_abertura;
            statusRValue.value = ordem_servicoSelected.value.id_req.status;
            cnpjValue.value = ordem_servicoSelected.value.id_req.fk_cliente_id.cnpj;
            inspecaoValue.value = ordem_servicoSelected.value.tipo_inspecao;
            responsavelValue.value = ordem_servicoSelected.value.id_supervisor.nome;
            status_ordemValue.value = ordem_servicoSelected.value.status_ordem;
            descricaoValue.value = ordem_servicoSelected.value.id_req.descricao;
            checklValue.value = ordem_servicoSelected.value.id_check.nome;

  } catch (error) {
    console.error(error);
  }

});

async function waitForIdInRoute(router: Router) {
  while (router.currentRoute.value.query.id === undefined) {
    await new Promise((resolve) => setTimeout(resolve, 100));
  }
}
</script>
