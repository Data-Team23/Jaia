<template>
    <h2>Adicionar cliente</h2>
    <form action="" class="add-form">
        <div class="input-inline-field">
            <InputField label="CNPJ:" placeholder="Informe o cnpj:">
            </InputField>
            <InputField label="Telefone:" placeholder="Informe o telefone:">
            </InputField>
        </div>
        <div class="input-inline-field">
            <InputField label="Nome:" placeholder="Informe o nome:">
            </InputField>
            <InputField label="Plano:" placeholder="Informe o telefone:">
            </InputField>
        </div>
        <div class="input-inline-field">
            <InputField label="Endereço:" placeholder="Informe o endereço:">
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
</script>