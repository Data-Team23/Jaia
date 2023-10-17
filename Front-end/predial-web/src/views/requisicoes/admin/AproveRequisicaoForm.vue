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
      <div class="select-input">
        <label for="responsavel">Responsável</label>
        <select class="input-select" id="responsavel" v-model="responsavelValue">
          <option value="">Selecione um Responsável</option>
          <option v-for="funcionario in funcionarios" :key="funcionario.id" :value="funcionario.id">
            {{ funcionario.nome }}
          </option>
        </select>
      </div>
    </div>
    <div class="input-inline-field">
      <InputField 
        type="datetime-local"
        label="Data da prestação de Serviço" 
        placeholder="Informe a data"
        v-model="dataValue"
      ></InputField>
      <div class="select-input">
        <label for="checklist">Checklist</label>
        <select class="input-select" id="checklist" v-model="checklValue">
          <option value="">Selecione um Checklist</option>
          <option v-for="item in checklist" :key="item.id" :value="item.id">
            {{ item.nome }}
          </option>
        </select>
      </div>
    </div>
    <div class="send-button">
      <InputButton text-button="Criar OS" @click="createOS" />
    </div>
  </form>
</template>

<script setup lang="ts">
import InputButton from '@/components/Button/InputButton.vue';
import InputField from '@/components/InputField/InputField.vue';
import { onMounted, ref } from 'vue';
import axios from 'axios';
import router from '@/router';
import type IRequisition from '../IRequisition';
import type { Router } from 'vue-router';
import type IFuncionario from '@/views/funcionario/IFuncionario';
import type ICheckList from '@/views/checklist/ICheckList';

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

const requisicaoSelected = ref<IRequisition>();
const funcionarios = ref<IFuncionario[]>([]);
const checklist = ref<ICheckList[]>([]);

onMounted(async () => {
  await waitForIdInRoute(router);
  const id = router.currentRoute.value.query.id;

  try {
    const [requisicaoResponse, funcionarioResponse, checklistResponse] = await Promise.all([
      axios.get<IRequisition>(`http://localhost:8080/requisicao/${id}`),
      axios.get<IFuncionario[]>('http://localhost:8080/funcionario'),
      axios.get<ICheckList[]>('http://localhost:8080/checklist'),
    ]);

    if (requisicaoResponse.data) {
      requisicaoSelected.value = requisicaoResponse.data;
      nome_ordemValue.value = requisicaoSelected.value.nome;
      dataaberturaValue.value = requisicaoSelected.value.data_abertura;
      statusRValue.value = requisicaoSelected.value.status;
      descricaoValue.value = requisicaoSelected.value.descricao;
      cnpjValue.value = requisicaoSelected.value.fk_cliente_id.cnpj;
      inspecaoValue.value = requisicaoSelected.value.inspecao;
    }

    if (funcionarioResponse.data) {
      funcionarios.value = funcionarioResponse.data;
    }

    if (checklistResponse.data) {
      checklist.value = checklistResponse.data;
    }

    console.log(requisicaoResponse.data);
  } catch (error) {
    console.error('Erro ao buscar dados:', error);
  }
});

async function waitForIdInRoute(router: Router) {
  while (router.currentRoute.value.query.id === undefined) {
    await new Promise((resolve) => setTimeout(resolve, 100));
  }
}

async function createOS() {
  event?.preventDefault();

  const requisicao = {
    nome: nome_ordemValue.value,
    inspecao: inspecaoValue.value,
    descricao: descricaoValue.value,
    status: statusRValue.value,
    data_abertura: dataaberturaValue.value,
    fk_cliente_id: {
      cnpj: cnpjValue.value,
    },
    fk_responsavel_id: responsavelValue,
    fk_checklist_id: checklValue,
  };

  try {
    const response = await axios.post('http://localhost:8080/ordem-servico/criar', requisicao);
    window.alert('Ordem de serviço criada com sucesso');
    nome_ordemValue.value = '';
    inspecaoValue.value = '';
    descricaoValue.value = '';
    statusRValue.value = '';
    dataaberturaValue.value = '';
    cnpjValue.value = '';
  } catch (error) {
    console.error('Erro ao criar a Ordem de serviço:', error);
    window.alert('Erro ao criar a Ordem de serviço');
  }
}
</script>
