<template>
    <div class="nav-bar-container">

        <figure>
            <img src="../../assets/logo-predial.png" alt="logo-predial">
        </figure>
        <ul>
            <li :class="{ 'active': $route.path === '/clientes' }">
                <div class="nav-text">
                    <div class="radius-top"></div>
                    <router-link to="/clientes" class="router-link">
                        Cliente
                    </router-link>
                    <div class="radius-bottom"></div>
                </div>
            </li>
        
            <li :class="{ 'active': $route.path === '/departamentos' }">
                <div class="nav-text">
                    <div class="radius-top"></div>
                    <router-link to="/departamentos" class="router-link">
                        Departamentos
                    </router-link>
                    <div class="radius-bottom"></div>
                </div>
            </li>
        
            <li :class="{ 'active': $route.path === '/funcionarios' }">
                <div class="nav-text">
                    <div class="radius-top"></div>
                    <router-link to="/funcionarios" class="router-link">
                        Funcionários
                    </router-link>
                    <div class="radius-bottom"></div>
                </div>
            </li>
        
            <li :class="{ 'active': $route.path === '/requisicoes' }">
                <div class="nav-text">
                    <div class="radius-top"></div>
                    <router-link to="/requisicoes" class="router-link">
                        Requisições
                    </router-link>
                    <div class="radius-bottom"></div>
                </div>
            </li>
            <li :class="{ 'active': $route.path === '/ordens-de-servico' }">
                <div class="nav-text">  
                    <div class="radius-top"></div>
                    <router-link to="/ordens-de-servico" class="router-link">
                        Ordem de serviços
                    </router-link>
                    <div class="radius-bottom"></div>
                </div>
            </li>
            <li :class="{ 'active': $route.path === '/check-list' }">
                <div class="nav-text">
                    <div class="radius-top"></div>
                    <router-link to="/check-list" class="router-link">
                        Checklist
                    </router-link>
                    <div class="radius-bottom"></div>
                </div>
            </li>

        </ul>
        <br><br>
        <div class="nav-button">
            <button class="button-logout">
                <router-link to="/">
                    Sair
                </router-link>
            </button>
        </div>           

    </div>
</template>

<script setup lang="ts">
    import './style.css'
</script>