<script setup lang="ts">
  import NavBar from './components/shared/NavBar.vue';
  import { useRoute } from 'vue-router';

  const route = useRoute();
  const showNavBar = route.name !== null && route.name !== undefined && route.name !== 'login';
  console.log(route.name)
</script>

<template>  
  <header style="position: absolute; top: 0%; left: 0%;" v-if="showNavBar">
    <NavBar></NavBar> 
  </header>
  <RouterView></RouterView>
</template>

<style scoped>

</style>
