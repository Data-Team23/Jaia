<script setup lang="ts">
  import NavBar from './components/shared/NavBar.vue';
  import { useRoute } from 'vue-router';



  const route = useRoute();
  
</script>

<template>  
  <header style="position: absolute; top: 0%; left: 0%;" v-if="!$route.meta.hideNavBar">
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
</style>
