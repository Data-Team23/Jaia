<template>
  <div class="form-title">
    <h2>Adicionar requisição</h2>
  </div>
  <form action="" class="add-form">
    <div class="input-inline-field">
      <InputField
        label="Nome:"
        placeholder="Informe o nome"
        v-model="nameValue"
      >
      </InputField>
      <SelectField
        label="Status requisição:"
        :option-values="optionsStatusReq"
        v-model="statusReqValue"
        value-prop="value"
        display-prop="label"
      >
      </SelectField>
    </div>
    <div class="input-inline-field">
      <InputField
        label="Data Abertura:"
        v-model="dateValue"
        type="datetime-local"
      >
      </InputField>
      <InputField
        label="Descrição:"
        placeholder="Descritivo da requisição"
        v-model="describeValue"
      >
      </InputField>
    </div>
    <div class="input-inline-field">
      <InputField
        label="CNPJ:"
        v-model="cnpjValue"
        placeholder="00.000.000/0000-00"
      >
      </InputField>
    </div>
    <div class="input-inline-field">
      <SelectField
        label="Inspeção:"
        v-model="inspectionValue"
        placeholder="Inspeção"
        :option-values="optionsInspecao"
        value-prop="value"
        display-prop="label"
      >
      </SelectField>
    </div>
    <div class="send-button">
      <InputButton text-button="ENVIAR" @click="createRequisition"></InputButton>
    </div>
  </form>
</template>

<script setup lang="ts">
import { ref } from "vue";
import InputField from "@/components/InputField/InputField.vue";
import SelectField from "@/components/Select/SelectField.vue";
import InputButton from "@/components/Button/InputButton.vue";
import axios from 'axios';

const nameValue = ref("")
const statusReqValue = ref("")
const describeValue = ref("")
const dateValue = ref("")
const cnpjValue = ref("")
const statusOsValue = ref("")
const inspectionValue = ref("")
const cliente = ref()

const optionsStatusReq = [
  {
    label: "Enviada",
    value: "enviada",
  },
  {
    label: "Visualizada",
    value: "visualizada",
  },
  {
    label: "Fechada",
    value: "fechada",
  },
];

const optionsStatusOs = [
  {
    label: "Enviada",
    value: "enviada",
  },
  {
    label: "Visualizada",
    value: "visualizada",
  },
  {
    label: "Fechada",
    value: "fechada",
  },
];
const optionsInspecao = [
  {
    label: "Periódica",
    value: "periodica",
  },
  {
    label: "Eventual",
    value: "eventual",
  },
  {
    label: "Especial",
    value: "especial",
  },
  {
    label: "Geral",
    value: "geral",
  },
];

async function createRequisition(){

  cliente.value = (await axios.get("http://localhost:8080/cliente/" + cnpjValue.value)).data;

  await axios.post("http://localhost:8080/requisicao/"+ cliente.value.id,
                    {
                      nome:nameValue.value,
                      inspecao:inspectionValue.value,
                      descricao:describeValue.value,
                      status:statusReqValue.value,
                      data_abertura: dateValue.value
                    }).then(response => {
                      console.log(response)
                    })
}


</script>
