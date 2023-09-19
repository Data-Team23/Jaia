<template>
    <div id="landingPage">
  
      <div class="navbar">
        <img src="@/assets/logo-landing-predial.png" alt="Logo" class="navbar-logo" />
        <div class="navbar-links">
          <a href="#home" class="navbar-item">Home</a>
          <a href="#solucoes" class="navbar-item">Soluções</a>
          <a href="#planos" class="navbar-item">Planos</a>
          <a href="#fale-conosco" class="navbar-item">Fale Conosco</a>
        </div>
      </div>
  
      <section id="space">
        <div class="wrapper">asda
        </div>
      </section>

      <section id="home">
        <div class="wrapper">
  <div class="">
    <h2 class="title">O que é a Predial?</h2>
        
        <button class="button" style="width: max-content; margin-top:100px;"><img src="@/assets/whats.png" alt="Logo" class="whats-logo" />Fale Conosco</button>
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

    <!-- Seção Planos -->
    <p class="title">Planos</p>

    <div id="planos" class="section-wrapper">
      <div class="container" v-for="(container, index) in planosContainers" :key="'planos-' + index">
            <img :src="container.imgSrc" :alt="container.text"  class="img-container">
        <div class="content">
          <div class="text-container">{{ container.text }}</div>
          <button class="button btn-detail" @click="openModal(container)">Ver detalhes</button>
        </div>
      </div>
    </div>
      <section id="fale-conosco">
        <h2 class="title">Fale Conosco</h2>
        <!-- Conteúdo da seção Fale Conosco vai aqui -->
      </section>
  
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

  </template>
<script>
export default {
  name: 'MyView',

  mounted() {
    document.body.style.backgroundColor = '#f5f5f5';  // Escolha sua cor preferida
  },

  beforeDestroy() {
    document.body.style.backgroundColor = '';  // Isso reverterá para a cor de fundo original
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
      planosContainers: [
        {
          imgSrc: "src/assets/solucoes2.png",
          text: "Plano de 12 Meses",
          title: "Plano de 12 Meses",
          description: "Este plano oferece um ano completo de serviços de manutenção predial, garantindo a integridade e segurança da sua edificação. Ideal para edifícios ou instalações novas que desejam manter sua estrutura em perfeito estado. O pacote inclui inspeções periódicas, atendimento a ordens de serviço e emissão de laudos técnicos."
        },
        {
          imgSrc: "src/assets/solucoes2.png",
          text: "Plano de 24 Meses",
          title: "Plano de 24 Meses",
          description: "Com dois anos de cobertura, este plano é perfeito para edifícios que necessitam de um cuidado mais prolongado, garantindo uma manutenção contínua. Além das inspeções regulares, o pacote oferece um acompanhamento mais detalhado, identificando e corrigindo problemas antes que se tornem críticos. Garanta a longevidade e segurança da sua estrutura com nosso plano de 24 meses."
        },
        {
          imgSrc: "src/assets/solucoes2.png",
          text: "Plano de 36 Meses",
          title: "Plano de 36 Meses",
          description: "Nosso pacote premium oferece três anos de serviços ininterruptos de manutenção predial. Recomendado para edifícios mais antigos ou com maior fluxo de pessoas, este plano oferece inspeções mais frequentes e um atendimento prioritário para ordens de serviço. Com um acompanhamento detalhado, nosso time de especialistas garantirá que sua edificação permaneça segura e funcional por muito mais tempo."
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
  
  
  <style scoped>

  .title{
color: #273A34;
font-size: 40px;
font-family: Poly;
font-weight: 400;
word-wrap: break-word;
text-align: center;
  }

  .navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0;
  background-color: #737373;
  position: absolute !important;
  top: 0 !important;
  left: 0;
  width: 100%;
  z-index: 1000;
}

.navbar-logo, .navbar-item {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
}

.navbar-logo {
  width: 65px;
  height: 65px;
}

.navbar-links {
  display: flex;
  align-items: center;
  justify-content: space-between;
  flex: 4; /* Porque temos 4 links */
  padding: 0 10px; /* Ajuste conforme necessário para os links não tocarem nas bordas */
}

.navbar-item {
  color: white;
  text-decoration: none;
  transition: color 0.3s;
}

.navbar-item:hover {
  color: #ddd;
}

#space {
    display: contents;
  position: absolute !important;
  top: 0 !important;
}

#home {
    flex-direction: column;
    display: flex;
    justify-content: space-between;
    margin-top: 400px;
    align-items: center;
}

.home-img-container{
    text-align: center;
    margin-left: 250px;
}

section {
  color: white;
}

.button {
    border-radius: 5px;
}

.whats-logo {
    border-radius: 5px;
    height: 27px;
    width: 27px;
    margin-right:5px;
}
.wrapper {
  display: flex;  /* Faz os contêineres ficarem lado a lado */
}

.container, .img-container {
  flex: 1;  
  height: 200px;  
}

.container {
  display: flex;
  flex-direction: column;  
  justify-content: space-between;  
  padding: 20px;
  align-items: baseline;
  border-radius:10px;
}

.img-container {
  justify-content: center;
  max-height: 50%;
  border-radius:10px;
}

.img-container img {
}

.section-wrapper {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px; /* Espaço entre as duas linhas de caixas */
}

.container {
  border: 1px solid #ccc;
  box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);
  flex: 1;
  margin: 25px;
  padding: 0;
  
}

.img-container div {
  width: 100%;
  max-height: 50%;
  background-size: cover;
  background-position: center;
}

.content {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  height: 50%;
  align-self: center;
}

.content div {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
}

.btn-detail {
 color: #666666;
font-size: 10px;
font-family: Poppins;
font-weight: 600;
word-wrap: break-word
}
.text-container{
    color: #273A34;
font-size: 15px;
font-family: Poly;
font-weight: 400;
word-wrap: break-word;
margin-top: 5px; 
}
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.7);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal {
  background: #B3B3B3;
  width: 70%;
  height: 60%;
  padding: 20px;
  position: relative;
  box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.3);
  border-radius: 10px;
}

.modal-header {
    color: white;
font-size: 24px;
font-family: Poppins;
font-weight: 700;

}

.modal-content {
  display: flex;
  height: 100%;
  color: white;
font-size: 14px;
font-family: Poppins;
font-weight: 700;
}

.modal-content .text-content {
  flex: 1;
  padding: 20px;
}

.modal-content img {
  flex: 1;
  object-fit: cover;
  border-radius:10px;
}

.modal-content .btn-contact {
  position: absolute;
  bottom: 20px;
  right: 20px;
  padding: 10px 15px;
  color: #737373;
font-family: Poppins;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
  </style>