<template>
    <div class="container">
        <div class="login-box">
            <form action="">
                <div class="input-field">
                    <InputField
                        label="CNPJ:"
                        v-model="inputCNPJ"
                        type="text"
                        placeholder="Inform o cnpj"
                    >
                    </InputField>
                </div>
                <div class="input-field">
                    <InputField
                        label="Senha:"
                        v-model="inputPassword"
                        type="password"
                        placeholder="Informe a senha"
                    >
                    </InputField>
                </div>
                <div class="button">
                    <span class="material-symbols-outlined">
                        login
                    </span>
                    <button class="form-button"  style="color: #666666;" @click="handleLogin()"> 
                        Login
                    </button>
                </div>                
            </form>
        </div>
    </div>
</template>

<script setup lang="ts">
    import InputField from '@/components/InputField/InputField.vue';
    import './style.css'
    import { ref } from 'vue';
    import { watchEffect } from 'vue';
    import router from '@/router';
    
    const inputCNPJ = ref('')
    const inputPassword = ref('')

    function handleLogin(){
        const credentials = {
            cnpj: inputCNPJ.value,
            password: inputPassword.value
        }

        console.log(credentials)

        router.push('/clientes')
    }

    
</script>