<template>
  <div class="form-title">
    <h2>Cadastrar Ordem de Serviço</h2>
  </div>
  <form action="" class="add-form">
    <div class="input-inline-field">
      <InputField
        label="Nome"
        placeholder="Informe o nome"
        v-model="nome_ordemValue"
      >
      </InputField>
      <InputField
        label="Código da Requisição"
        placeholder="Informe o código"
        v-model="reqValue"
      >
      </InputField>
    </div>
    <div class="input-inline-field">
      <SelectField 
        label="Selecione o tipo de Inspeção:" 
        :option-values="inspectTypes"
        value-prop="value" 
        display-prop="label" 
        v-model="inspecaoValue">
      </SelectField>  
      <SelectField 
        label="Selecione o Checklist:" 
        :option-values="checklists" 
        value-prop="id" 
        display-prop="nome"
        v-model="checklValue">
      </SelectField> 
    </div>
    <div class="input-inline-field">
      <InputField
        label="Status da Ordem de Serviço"
        placeholder="Informe status da Ordem de Serviço"
        v-model="status_ordemValue"
      >
      </InputField>    
      <SelectField 
        label="Selecione o Responsável:" 
        :option-values="funcionarios" 
        value-prop="id" 
        display-prop="nome"
        v-model="responsavelValue">
      </SelectField>
    </div>
    <div class="input-inline-field">
      <!-- <InputField
        label="Data da prestação de Serviço"
        placeholder="Informe a data"
        v-model="dataValue"
        type="datetime-local"
      >
      </InputField> -->
          
    </div>
    <label>Informe a descrição</label>
    <textarea
      label="Descrição"
      placeholder="Descrição"
      v-model="descricaoValue"
      rows="5"
    >
    </textarea>
    <div class="send-button">
      <InputButton
        text-button="Salvar"
        @click="createOrdemServico"
      ></InputButton>
    </div>
  </form>
</template>

<script setup lang="ts">
import InputField from "@/components/InputField/InputField.vue";
import InputButton from "@/components/Button/InputButton.vue";
import SelectField from "@/components/Select/SelectField.vue";
import { ref } from "vue";
import axios from "axios";
import type ICheckList from "@/views/checklist/ICheckList";
import type IFuncionario from "@/views/funcionario/IFuncionario";
import { onMounted } from "vue";

const nome_ordemValue = ref("");
const statusRValue = ref("");
const DataAberturaValue = ref("");
const descricaoValue = ref("");
const cnpjValue = ref("");
const status_ordemValue = ref("");
const inspecaoValue = ref("");
const responsavelValue = ref("");
const dataValue = ref("");
const checklValue = ref("");
let checklists = ref();
const reqValue = ref();
let funcionarios = ref();

async function createOrdemServico() {
  event?.preventDefault();

  const ordem_servico = {
    status_ordem: status_ordemValue.value,
    nome_ordem: nome_ordemValue.value,
    tipo_inspecao: inspecaoValue.value,
    idChecklist: checklValue.value,
    idSupervisor: responsavelValue.value,
    idReq: reqValue.value,
  };

  try {
    const response = await axios.post(
      "http://localhost:8080/ordem-servico/criar",
      ordem_servico
    );
    console.log(response);
    window.alert("Ordem criada com sucesso");
    location.reload();
  } catch (error) {
    console.error("Erro ao criar Ordem de Serviço:", error);
    window.alert("Erro ao criar Ordem de Serviço");
  }
}

function listFuncionarios() {
  axios
    .get<IFuncionario>("http://localhost:8080/funcionario")
    .then((response: any) => {
      funcionarios.value = response.data;
    })
    .catch((error: any) => {
      console.error("Erro ao buscar funcionários:", error);
    });
}

function listChecklist() {
  axios
    .get<ICheckList>("http://localhost:8080/checklist")
    .then((response: any) => {
      checklists.value = response.data;
    })
    .catch((error: any) => {
      console.error("Erro ao buscar checklists:", error);
    });
}

const inspectTypes = [
  {
    label: "Eventual",
    value: "Eventual"
  },
  {
    label: "Periódica",
    value: "Periódica"
  },
  {
    label: "Geral",
    value: "Geral"
  },
  {
    label: "Preventiva",
    value: "Preventiva"
  },
]

onMounted(() => {
  listFuncionarios()
  listChecklist()
})
</script>

<style>
  textarea{
    background-color: var(--input-field-color);
    color: #FFFFFF;
    padding: 10px;
    border-radius: 10px;
    width: 100%;
    margin-bottom: 20px;
  }
  label{
    color: #FFFFFF;
    font-size: 18px;
    text-align: start;
    width: 100%;
  }
</style>
