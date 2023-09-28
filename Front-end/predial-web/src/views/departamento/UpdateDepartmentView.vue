<template>
  <div class="form-title">
    <h2>Atualizar departamento</h2>
  </div>
  <form action="" class="add-form">
    <div class="input-inline-field">
      <InputField
        label="Nome:"
        placeholder="Informe o nome"
        v-model="nameValue"
      >
      </InputField>

      <InputField
        label="Código:"
        placeholder="Informe o código"
        v-model="codeValue"
      >
      </InputField>
    </div>
    <div class="send-button">
      <InputButton text-button="Salvar"></InputButton>
    </div>
  </form>
</template>

<script setup lang="ts">
import InputField from "@/components/InputField/InputField.vue";
import InputButton from "@/components/Button/InputButton.vue";
import { onMounted, ref } from "vue";
import { useRouter, type Router } from "vue-router";
import axios from "axios";
import type IDepartament from "./IDepartament";

const router = useRouter();

const codeValue = ref("");
const responsibleValue = ref("");
const nameValue = ref("");
const departamentoSelected = ref<IDepartament>();

onMounted(async () => {
  await waitForCodDepartInRoute(router);
  const codDepart = router.currentRoute.value.query.codDepart;
  try {
    axios
      .get<IDepartament>(`http://localhost:8080/departamentos/${codDepart}`)
      .then((response) => {
        if (response.data) {
          departamentoSelected.value = response.data;
          codeValue.value = departamentoSelected.value.codDepart.toString();
          nameValue.value = departamentoSelected.value.nome;
        }
      });
  } catch (error) {
    console.log(error);
  }
});

async function waitForCodDepartInRoute(router: Router) {
  // Loop para aguardar até que route.query.cnpj esteja definido
  while (router.currentRoute.value.query.codDepart === undefined) {
    // Definição do período de tempo (pode ser ajustado)
    await new Promise((resolve) => setTimeout(resolve, 100));
  }
}
</script>
