<template>
    <div class="form-title">
        <h2>Atualizar cliente</h2>
    </div>
    <form action="" class="add-form">
        <div class="input-inline-field">
            <InputField 
                label="Checklist:" 
                placeholder="Informe o checklist"
                v-model="checkListValue">
            </InputField>
            <InputField 
                label="Departamento:" 
                placeholder="Informe o departamento:"
                v-model="departamentoValue">
            </InputField>
        </div>
        <div class="send-button">
            <InputButton text-button="Salvar" @click="updateCheckList"></InputButton>
        </div>
    </form>

</template>

<script setup lang="ts">
import { ref } from 'vue';
import InputField from '@/components/InputField/InputField.vue';
import InputButton from '@/components/Button/InputButton.vue';


const checkListValue = ref("")
const departamentoValue = ref("")

function updateCheckList(){
    console.log("updated")
}

</script>