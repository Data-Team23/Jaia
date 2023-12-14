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
            <button
              type="button"
              class="btn btn-outline-primary"
              :class="{ active: selectedTipoDocumento === 'admin' }"
              @click="selectedTipoDocumento = 'admin'"
            >
              Admin
            </button>
          </div>
        </div>
        <div class="input-field">
          <InputField
            :label="selectedTipoDocumento === 'cnpj' ? 'CNPJ:' : 
                    (selectedTipoDocumento === 'cpf' ? 'CPF:' : 'Username:')"
            :placeholder="`Informe o ${selectedTipoDocumento === 'cnpj' ? 'CNPJ' : 
                           (selectedTipoDocumento === 'cpf' ? 'CPF' : 'Username')}`"
            :type="selectedTipoDocumento === 'cnpj' || selectedTipoDocumento === 'cpf' ? 'text' : 'text'"
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
  import router from '@/router';
  import { ref } from 'vue';
  import './style.css'
  import { useAuthStore } from '@/stores/authStore';
  import type AuthResponse from '@/stores/AuthResponse'

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
      const response = await axios.post<AuthResponse>('http://localhost:8080/api/auth/login', credentials);

      console.log('Resposta do servidor:', response.data);

      const authStore = useAuthStore();

      if (response.data.message && response.data.id) {
        const message = response.data.message.toLowerCase();
        if (message.includes('cliente autenticado')) {
          console.log('Cliente autenticado');
          authStore.toggleRequisicoesVisibility();
          authStore.setUserId(response.data.id);
          router.push('/requisicoes');
        } else if (message.includes('funcionário autenticado')) {
          console.log('Funcionário autenticado');
          authStore.toggleRequisicoesVisibility();
          authStore.setUserId(response.data.id);
          router.push('/ordens-de-servico');
        } else if (message.includes('administrador autenticado')) {
          console.log('Administrador autenticado');
          authStore.toggleRequisicoesVisibility();
          authStore.setUserId(response.data.id);
          router.push('/clientes');
        } else {
          console.log('Autenticação falhou');
          window.alert("Autenticação falhou. Verifique o console para mais detalhes.");
        }
      } else {
        console.log('Resposta do servidor não contém mensagem ou id:', response.data);
        window.alert("Erro na resposta do servidor. Verifique o console para mais detalhes.");
      }
    } catch (error) {
      console.error('Erro ao tentar autenticar:', error);
    }
  };
</script>./AuthResponse../../stores/AuthResponse