<script setup lang="ts">
  import NavBar from './components/NavBar/NavBar.vue';
  import { useRoute } from 'vue-router';



  const route = useRoute();
  
</script>

<template>  
  <header v-if="!$route.meta.hideNavBar">
    <NavBar></NavBar> 
  </header>
  <main>
    <RouterView></RouterView>
  </main>
</template>

<style>
  template{
    background-color: #3A3A5A;
    width: 100%;
    height: 100%;
  }
  main{
    display: flex !important;
    flex-direction: column;
    justify-content: center;
    width: 100%;
    padding: 0 30px 0 15px;
  }
</style>
