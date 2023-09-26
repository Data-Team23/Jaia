<template>
    <div class="form-title">
        <h2>Atualizar cliente</h2>
    </div>
    <form action="" class="add-form">
        <div class="input-inline-field">
            <InputField 
                label="CNPJ:" 
                value="00.000.000/0000-01"
                :model-value="cnpjValue">
            </InputField>
            <InputField 
                label="Telefone:" 
                placeholder="Informe o telefone:"
                :model-value="phoneValue">
            </InputField>
        </div>
        <div class="input-inline-field">
            <InputField 
                label="Nome:" 
                placeholder="Informe o nome:"
                :model-value="nameValue">
            </InputField>
            <InputField 
                label="E-mail:" 
                placeholder="Informe o e-mail:"
                :model-value="emailValue">
            </InputField>
        </div>
        <div class="input-inline-field">
            <InputField 
                label="Endereço:" 
                placeholder="Informe o endereço:"
                :model-value="addressValue">
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
    import SelectField from '@/components/Select/SelectField.vue';
    import { onMounted, ref } from 'vue';
    import { globalCnpj } from './GlobalCnpj'; 
    import axios from 'axios';

    
    const cnpjValue = ref('')
    const phoneValue = ref('')
    const nameValue = ref('')
    const emailValue = ref('')
    const addressValue = ref('')
    const selectedCnpj = globalCnpj;

    function infClient() {
    axios.get<any>(`http://localhost:8080/cliente/por-cnpj/${selectedCnpj.value}`)
        .then((response: any) => {
            const clientData = response.data;

            cnpjValue.value = clientData.cnpj || '';
            phoneValue.value = clientData.telefone || '';
            nameValue.value = clientData.nome || '';
            emailValue.value = clientData.email || '';
            addressValue.value = clientData.logradouro || '';
        })
        .catch((error: any) => {
            console.error('Erro ao buscar cliente:', error);
        });
}

    onMounted(() => {
    infClient();
    })


    async function updateClient() {
    
    }

</script>