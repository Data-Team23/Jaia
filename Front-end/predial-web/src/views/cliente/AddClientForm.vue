<template>
    <div class="form-title">
        <h2>Adicionar cliente</h2>
    </div>
    <form action="" class="add-form">
        <div class="input-inline-field">
            <InputField label="CNPJ:" placeholder="00.000.000/0000-01">
            </InputField>
            <InputField label="Telefone:" placeholder="(12) 996534789">
            </InputField>
        </div>
        <div class="input-inline-field">
            <InputField label="Nome:" placeholder="Informe o nome">
            </InputField>
            <SelectField label="Plano:" :option-values="options">
            </SelectField>
        </div>
        <div class="input-inline-field">
            <InputField label="Endereço:" placeholder="Rua Itajaí n° 435">
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
    import SelectField from '@/components/Select/SelectField.vue';

    const options = [
        'Gold',
        'Silver',
        'Bronze'
    ]
</script>