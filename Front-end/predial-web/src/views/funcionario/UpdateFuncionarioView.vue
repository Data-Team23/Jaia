<template>
  <div class="form-title">
    <h2>Atualizar funcionário</h2>
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
        v-model="telefoneValue">
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
import type IFuncionario from './IFuncionario';
import axios from 'axios';

const router = useRouter()

const cpfValue = ref('');
const departmentValue = ref('');
const emailValue = ref('');
const nameValue = ref('');
const senhaValue = ref('');
const telefoneValue = ref('');

const funcionarioSelected = ref<IFuncionario>()

onMounted(async () => {
  await waitForIdInRoute(router);
  const id = router.currentRoute.value.query.id;
  try {
    axios
      .get<IFuncionario>(`http://localhost:8080/funcionario/${id}`)
      .then((response) => {
        if (response.data) {
          funcionarioSelected.value = response.data;
          console.log(funcionarioSelected.value)
          cpfValue.value = funcionarioSelected.value.cpf
          nameValue.value = funcionarioSelected.value.nome;
          telefoneValue.value = funcionarioSelected.value.telefone;
        }
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
