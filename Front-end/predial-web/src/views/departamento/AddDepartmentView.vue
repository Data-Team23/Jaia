<script setup lang="ts">
    import { ref } from 'vue';

    const name = ref('')
    const name2 = ref('')
    

</script>

<template>
    <h1>Adicionar departamento</h1>
    <v-form @submit.prevent>
        <v-text-field
            v-model="name"
            :rules="[(value) => (value !== null && value !== '' && value !== undefined)]"
            label="Informe o nome:"
            bg-color="#626288"
            class="input"         
        >
        </v-text-field>
    </v-form>
    <h1>{{ name }}</h1>
    <form action="">
        <label class="label">Informe o nome:<input type="text" class="input-field" v-model="name2"></label>       
    </form>
    <h1>{{ name2 }}</h1>
</template>

<style>
    h1{
        color: white;
    }
    .label{
        display: flex;
        flex-direction: column;
        color: white;
    }
    .input-field{
        background-color: #626288;
        height: 50px;
        border-radius: 20px;
        color: white;
        font-size: 18px;
        padding: 0 10px;
    }
</style>