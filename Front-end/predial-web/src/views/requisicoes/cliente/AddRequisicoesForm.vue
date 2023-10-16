<template>
  <div class="form-title">
    <h2>Adicionar cliente</h2>
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
        v-model="inspectionValue"
        placeholder="00.000.000/0000-00"
      >
      </InputField>
      <SelectField
        label="Status da O.S:"
        placeholder="Selecione"
        v-model="statusOsValue"
        :option-values="optionsStatusOs"
        value-prop="value"
        display-prop="label"
      >
      </SelectField>
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
      <SelectField
        label="Responsável:"
        v-model="statusOsValue"
        :option-values="optionsStatusOs"
        value-prop="value"
        display-prop="label"
      >
      </SelectField>
    </div>
    <div class="input-inline-field">
      <InputField
        label="Data da prestação do serviço:"
        v-model="inspectionValue"
        placeholder="01/01/2020"
      >
      </InputField>
      <SelectField
        label="Checklist:"
        placeholder="Informe o checklist"
        v-model="statusOsValue"
        :option-values="optionsStatusOs"
        value-prop="value"
        display-prop="label"
      >
      </SelectField>
    </div>
    <div class="send-button">
      <InputButton text-button="Criar O.S." @click="createRequisition"></InputButton>
    </div>
  </form>
</template>

<script setup lang="ts">
import { ref } from "vue";
import InputField from "@/components/InputField/InputField.vue";
import SelectField from "@/components/Select/SelectField.vue";
import InputButton from "@/components/Button/InputButton.vue";

const nameValue = ref("");
const statusReqValue = ref("");
const describeValue = ref("");
const dateValue = ref("")
const cnpjValue = ref("")
const statusOsValue = ref("")
const inspectionValue = ref("")

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

function createRequisition(){

}
</script>
