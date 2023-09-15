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
            <SelectField label="Plano:" :option-values="options" v-model="planeValue">
            </SelectField>
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

    const cnpjValue = ref("")
    const phoneValue = ref("")
    const nameValue = ref("")
    const planeValue = ref("")
    const addressValue = ref("")

    const options = [
        'Gold',
        'Silver',
        'Bronze'
    ]

    function createClient(){
        event?.preventDefault()
        const client = {
            cnpjValue: cnpjValue.value,
            phoneValue: phoneValue.value,
            nameValue: nameValue.value,
            planeValue: planeValue.value,
            addressValue: addressValue.value,
        }
        console.log(client)
    }
</script>