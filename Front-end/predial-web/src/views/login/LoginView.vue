<template>
    <div class="container">
      <div class="login-box">
        <form @submit.prevent="handleLogin">
          <div class="input-field">
            <div class="document" role="group" aria-label="Tipo de Documento">
              <label for="tipoDocumento">Tipo de Documento:</label>
              <button
                type="button"
                class="btn btn-outline-primary"
                :class="{ active: selectedTipoDocumento === 'cnpj' }"
                @click="selectedTipoDocumento = 'cnpj'"
              >
                CNPJ
              </button>
              <button
                type="button"
                class="btn btn-outline-primary"
                :class="{ active: selectedTipoDocumento === 'cpf' }"
                @click="selectedTipoDocumento = 'cpf'"
              >
                CPF
              </button>
            </div>
          </div>
          <div class="input-field">
            <InputField
              :label="selectedTipoDocumento === 'cnpj' ? 'CNPJ:' : 'CPF:'"
              :placeholder="`Informe o ${selectedTipoDocumento === 'cnpj' ? 'CNPJ' : 'CPF'}`"
              :type="selectedTipoDocumento === 'cnpj' ? 'text' : 'text'"
              v-model="inputDocumento"
            />
          </div>
          <div class="input-field">
            <InputField
              label="Senha:"
              v-model="inputPassword"
              type="password"
              placeholder="Informe a senha"
            />
          </div>
          <div class="button">
            <button class="form-button" style="color: #666666;" type="submit">
              Login
            </button>
          </div>
        </form>
      </div>
    </div>
  </template>
  
<script setup lang="ts">
    import InputField from '@/components/InputField/InputField.vue';
    import axios from 'axios';
    import './style.css'
    import router from '@/router';
    import { ref } from 'vue';
    import { showOnlyRequisicoes } from '@/stores/counter';
    
    const selectedTipoDocumento = ref('cnpj'); 
    const inputDocumento = ref('');
    const inputPassword = ref('');
    
    const handleLogin = async () => {
        const tipoDocumento = selectedTipoDocumento.value;
        const credentials = {
        username: inputDocumento.value,
        password: inputPassword.value,
        tipoDocumento: tipoDocumento,
        };
    
        try {
        const response = await axios.post('http://localhost:8080/api/auth/login', credentials);
    
        console.log('Resposta do servidor:', response.data);
    
        if (response.data.includes('Cliente autenticado')) {
            console.log('Cliente autenticado');
            showOnlyRequisicoes.value = true;
            router.push('/requisicoes');
        } else if (response.data.includes('Funcionário autenticado')) {
            console.log('Funcionário autenticado');
            showOnlyRequisicoes.value = false;
            router.push('/clientes');
        } else {
            console.log('Autenticação falhou');
            window.alert("Autenticação falhou. Verifique o console para mais detalhes.");
        }
        } catch (error) {
            console.error('Erro ao tentar autenticar:', error);
        }
    };
</script>