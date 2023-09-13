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
                label="Plano:" 
                placeholder="Informe o telefone:"
                :model-value="planeValue">
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
            <InputButton text-button="Salvar"></InputButton>
        </div>
    </form>
</template>

<script setup lang="ts">
    import InputField from '@/components/InputField/InputField.vue';
    import InputButton from '@/components/Button/InputButton.vue';
    import { ref } from 'vue';

    const cnpjValue = ref('09.888.344/0001-01')
    const phoneValue = ref('(12) 99689-5533')
    const nameValue = ref('João Carlos')
    const planeValue = ref('Master')
    const addressValue = ref('Rua Itajaí n° 245')
</script>