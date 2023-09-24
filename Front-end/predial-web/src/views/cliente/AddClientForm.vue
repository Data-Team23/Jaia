<template>
    <div class="form-title">
        <h2>Adicionar cliente</h2>
    </div>
    <form action="" class="add-form">
        <div class="input-inline-field">
            <InputField label="CNPJ:" placeholder="00.000.000/0000-01" v-model="cnpjValue">
            </InputField>
            <InputField label="Telefone:" placeholder="(12) 996534789" v-model="phoneValue">
            </InputField>
        </div>
        <div class="input-inline-field">
            <InputField label="Nome:" placeholder="Informe o nome" v-model="nameValue">
            </InputField>
            <InputField label="E-mail:" placeholder="Informe o email" v-model="emailValue">
            </InputField>
        </div>
        <div class="input-inline-field">
            <InputField label="Endereço:" placeholder="Rua Itajaí n° 435" v-model="addressValue">
            </InputField>
        </div>
        <div class="send-button">
            <InputButton text-button="Salvar" @click="createClient"></InputButton>
        </div>
    </form>
</template>

<script setup lang="ts">
import InputField from '@/components/InputField/InputField.vue';
import InputButton from '@/components/Button/InputButton.vue';
import SelectField from '@/components/Select/SelectField.vue';
import { ref } from 'vue';
import axios from 'axios';

const cnpjValue = ref("");
const phoneValue = ref("");
const nameValue = ref("");
const emailValue = ref("");
const addressValue = ref("");

async function createClient() {
    event?.preventDefault();

    const client = {
        cnpj: cnpjValue.value,
        phone: phoneValue.value,
        nome: nameValue.value, 
        plano: emailValue.value, 
        endereco: addressValue.value, 
    };

    try {
    const response = await axios.post('http://localhost:8080/cliente', client);
    cnpjValue.value = "";
    phoneValue.value = "";
    nameValue.value = "";
    emailValue.value = "";
    addressValue.value = "";
} catch (error) {
    console.error('Erro ao criar cliente:', error);
}
}

</script>
