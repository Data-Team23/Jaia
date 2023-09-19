<template>
    <div id="landingPage">
  
      <section id="space">
        <div class="wrapper">asda
        </div>
      </section>

      <section id="home">
        <div class="wrapper">
  <div class="">
    <h2 class="title">O que é a Predial?</h2>
        <button class="button"><img src="@/assets/whats.png" alt="Logo" class="whats-logo" />Fale Conosco</button>
  </div>
  <div class="home-img-container">
    <img src="@/assets/celular.png" alt="Celular com logo">
  </div>
</div>
    </section>

    <p class="title">Soluções</p>
    
    <div id="solucoes" class="section-wrapper">
      <div class="container" v-for="(container, index) in solucoesContainers" :key="'solucoes-' + index">
            <img :src="container.imgSrc" :alt="container.text"  class="img-container">
        <div class="content">
          <div class="text-container">{{ container.text }}</div>
          <button class="button btn-detail" @click="openModal(container)">Ver detalhes</button>
        </div>
      </div>
    </div>

    <div v-if="showModal" class="modal-overlay" @click="closeModal">
    <div class="modal" @click.stop>
      <div class="modal-header">
        Detalhes - {{ selectedContainer.text }} 
      </div> 
      <div class="modal-content">
        <div class="text-content">
          {{ selectedContainer.description }}
        </div>
        <div>
          <img :src="selectedContainer.imgSrc" :alt="selectedContainer.text">
          <button class="button btn-contact" style="width: max-content;"><img src="@/assets/whats.png" alt="Logo" class="whats-logo" /> Fale Conosco</button>
        </div>
      </div>
    </div>
  </div>
 </div>

  </template>

<script>
import './style.css'
export default {
  name: 'MyView',

  mounted() {
    document.body.style.backgroundColor = '#DEDEDE';  
  },

  beforeDestroy() {
    document.body.style.backgroundColor = '';  
  },

  data() {
    return {
      solucoesContainers: [
        {
          imgSrc: "src/assets/solucoes1.png",
          text: "Ordem de Serviço",
          title: "Ordem de Serviço",
          description: "Uma Ordem de Serviço é um documento administrativo utilizado para formalizar a solicitação de algum trabalho específico dentro de uma organização. No contexto da manutenção predial, serve para instruir equipes de manutenção sobre tarefas específicas, detalhando o que precisa ser feito, os materiais necessários e o prazo para conclusão."
        },
        {
          imgSrc: "src/assets/solucoes2.png",
          text: "Checklist de Inspeção",
          title: "Checklist de Inspeção",
          description: "O Checklist de Inspeção é uma lista detalhada de itens ou tarefas que devem ser verificados ou realizados durante uma inspeção predial. Serve como um guia para os inspetores garantirem que todos os aspectos de um edifício ou estrutura estão em boas condições e atendem aos padrões estabelecidos."
        },
        {
          imgSrc: "src/assets/solucoes3.png",
          text: "Laudo Técnico",
          title: "Laudo Técnico",
          description: "O Laudo Técnico é um documento elaborado por um profissional especializado, que atesta as condições técnicas de uma edificação ou sistema. No âmbito da manutenção predial, o laudo apresenta uma análise detalhada sobre o estado atual do edifício, identificando problemas, riscos e recomendando as devidas correções."
        }
      ],
      showModal: false,
      selectedContainer: null
    }
  },
  methods: {
    openModal(container) {
    this.selectedContainer = container;
    this.showModal = true;
  },
  closeModal() {
    this.showModal = false;
  } 
  }
}

</script>
  
