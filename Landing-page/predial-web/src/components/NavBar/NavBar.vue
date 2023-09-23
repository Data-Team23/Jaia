<template>
    <div class="nav-bar-container">
      <figure>
        <img class="navbar-logo" src="../../assets/logo-landing-predial.png" alt="logo-predial">
      </figure>
      <ul>
        <div class="navbar-links">
          <li>
            <a href="#" @click="scrollToTop" class="router-link">
              <img src="../../assets/home.png" alt="Home" class="nav-icon" style="vertical-align: middle;">
              Home
            </a>
          </li>
          <li>
            <a href="#solucoes" class="router-link">
              <img src="../../assets/vector.png" alt="Solucoes" class="nav-icon" style="vertical-align: middle;">
              Soluções
            </a>
          </li>
          <li>
            <a href="https://wa.me/5512982156294" target="_blank" class="router-link">
              <img src="../../assets/phone-call.png" alt="FaleConosco" class="nav-icon" style="vertical-align: middle;">
              Fale Conosco
            </a>
          </li>
        </div>  
      </ul>
    </div>
  </template>

<script setup lang="ts">
  import './style.css'

  const scrollToTop = () => {
  window.scrollTo({ top: 0, behavior: 'smooth' });
  };
</script>