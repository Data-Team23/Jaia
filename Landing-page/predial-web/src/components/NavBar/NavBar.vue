<template>
    <div class="nav-bar-container">
      <figure>
        <img class="navbar-logo" src="../../assets/logo-landing-predial.png" alt="logo-predial">
      </figure>
      <ul>
        <div class="navbar-links">
            <li :class="{ 'active': $route.path === '/home' }">
            <router-link to="/home" class="router-link">
              Home
            </router-link>
          </li>
    
          <li :class="{ 'active': $route.path === '/solucoes' }">
            <router-link to="/solucoes" class="router-link">
              Soluções
            </router-link>
          </li>
    
          <li :class="{ 'active': $route.path === '/fale-conosco' }">
            <router-link to="/fale-conosco" class="router-link">
              Fale Conosco
            </router-link>
          </li>
        </div>
       
      </ul>
    </div>
  </template>

<script setup lang="ts">
    import './style.css'
</script>