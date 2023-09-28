<template>
    <div class="form-title">
        <h2>Atualizar cliente</h2>
    </div>
    <form action="" class="add-form">
        <div class="input-inline-field">
            <InputField 
                label="CNPJ:" 
                value="00.000.000/0000-01"
                v-model="cnpjValue">
            </InputField>
            <InputField 
                label="Telefone:" 
                placeholder="Informe o telefone:"
                v-model="phoneValue">
            </InputField>
        </div>
        <div class="input-inline-field">
            <InputField 
                label="Nome:" 
                placeholder="Informe o nome:"
                v-model="nameValue">
            </InputField>
            <InputField 
                label="E-mail:" 
                placeholder="Informe o e-mail:"
                v-model="emailValue">
            </InputField>
        </div>
        <div class="input-inline-field">
            <InputField 
                label="Endereço:" 
                placeholder="Informe o endereço:"
                v-model="addressValue">
            </InputField>
        </div>
        <div class="send-button">
            <InputButton text-button="Salvar" @click="updateClient"></InputButton>
        </div>
    </form>
</template>

<script setup lang="ts">
import InputField from '@/components/InputField/InputField.vue';
import InputButton from '@/components/Button/InputButton.vue';
import { onMounted, ref } from 'vue';
import axios from 'axios';
import { type Router, useRouter } from 'vue-router';
import type IClient from './IClient';

const router = useRouter();
const cnpjValue = ref('');
const phoneValue = ref('');
const nameValue = ref('');
const emailValue = ref('');
const addressValue = ref('');
const mensagemSucesso = ref('');

const clientSelected = ref<IClient>()

onMounted(async () => {
  await waitForCnpjInRoute(router);
  const clienteCnpj = router.currentRoute.value.query.cnpj;
  try {
    axios.get<IClient>(`http://localhost:8080/cliente/${clienteCnpj}`).then((response) => {
      if(response.data){
        clientSelected.value = response.data
        cnpjValue.value = clientSelected.value.cnpj
        phoneValue.value = clientSelected.value.telefone
        nameValue.value = clientSelected.value.nome
        addressValue.value = clientSelected.value.endereco
        emailValue.value = clientSelected.value.email
      }
    })
  } catch (error) {
    console.log(error)
  }
})

async function waitForCnpjInRoute(router: Router) {
  // Loop para aguardar até que route.query.cnpj esteja definido
  while (router.currentRoute.value.query.cnpj === undefined) {
    // Definição do período de tempo (pode ser ajustado)
    await new Promise((resolve) => setTimeout(resolve, 100));
  }
}

async function updateClient() {
  event?.preventDefault()
  try {
    const clienteCnpj = router.currentRoute.value.query.cnpj;
    const clienteAtualizado = {
      nome: nameValue.value, 
      telefone: parseFloat(phoneValue.value),
      senha: null,
      email: emailValue.value, 
      endereco: addressValue.value, 
    };

    await axios.put(`http://localhost:8080/cliente/atualizar/${clienteCnpj}`, clienteAtualizado).then((response) => {
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


