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
            <SelectField 
                label="Plano:" 
                :option-values="options"
                v-model="planeValue"
                value-prop="value" 
                display-prop="label">
            </SelectField>
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
    import { ref } from 'vue';

    const options = [
        {
            label: 'Gold',
            value: 'gold'
        },
        {
            label: 'Silver',
            value: 'silver'
        },
        {
            label: 'Bronze',
            value: 'bronze'
        },
    ]
    
    const cnpjValue = ref('09.888.344/0001-01')
    const phoneValue = ref('(12) 99689-5533')
    const nameValue = ref('João Carlos')
    const planeValue = ref(options[0].value)
    const addressValue = ref('Rua Itajaí n° 245')

    function updateClient(){
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