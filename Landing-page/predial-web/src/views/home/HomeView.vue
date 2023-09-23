<template>
    <div class="landing-page" id="landingPage">
        <section id="home">
            <div class="wrapper">
                <div class="home-titulo">
                    <h2 class="title">O que é a Predial?</h2>
                    <p class="tittle-somos">
                        A Predial é a sua solução confiável para inspeções prediais sob medida. Somos uma plataforma dedicada a atender às necessidades dos nossos clientes que buscam inspecionar seus edifícios com eficiência e precisão.
                        <br />
                        <br />
                        Concebida pela Jaia, a Predial permite que proprietários de edifícios, gerentes e administradores solicitem inspeções personalizadas para seus imóveis. Nossa plataforma intuitiva facilita o processo de agendar e coordenar inspeções, além de oferecer uma maneira eficaz de documentar detalhes relevantes e capturar evidências visuais.
                        <br />
                        <br />
                        Com a Predial, você pode contar com relatórios instantâneos que embasam decisões fundamentadas. Elevamos o padrão das inspeções prediais, contribuindo para uma maior segurança e excelência nas estruturas urbanas. Confie na Predial para cuidar das suas necessidades de inspeção predial de forma eficiente e confiável.
                    </p>
                    <a href="https://wa.me/5512982156294" target="_blank">
                        <button class="button">
                        <img src="@/assets/whats.png" alt="Logo" class="whats-logo" />
                        Fale Conosco
                        </button>
                    </a>
                </div>
                <div class="home-img">
                <img class="celular" src="@/assets/celular.png" alt="Celular com logo">
                </div>
            </div>  
        </section>
        <div class="solucoes" id="solucoes">
            <p class="title">Soluções</p>
            <div class="container">   
                <div class="ordem-servico box">
                    <img src="@/assets/solucoes1.png" alt="Ordem de Serviço"/>
                    <h>Ordem de Serviço</h>
                    <span class="button-solucoes" id="detalhes-os" @click="detalhesOS = true">Ver detalhes</span>
                    <v-dialog v-model="detalhesOS" width="80%">
                            <OrdemServicoForm></OrdemServicoForm>
                    </v-dialog>
                </div>
                <div class="checklist box">
                    <img src="@/assets/solucoes2.png" alt="Checklist"/>
                    <h>Checklist</h>
                    <span class="button-solucoes" id="detalhes-ck" @click="detalhesCK = true">Ver detalhes</span>
                    <v-dialog v-model="detalhesCK" width="80%">
                            <ChecklistForm></ChecklistForm>
                    </v-dialog>
                </div>
                <div class="laudo-tecnico box">
                    <img src="@/assets/solucoes3.png" alt="Laudo Tecnico"/>
                    <h>Laudo Técnico</h>
                    <span class="button-solucoes" id="detalhes-lt" @click="detalhesLT = true">Ver detalhes</span>
                    <v-dialog v-model="detalhesLT" width="80%">
                            <LaudoTecnicoForm></LaudoTecnicoForm>
                    </v-dialog>
                </div>
            </div>
        </div>
        <div class="footer-box">
            <img src="@/assets/logo-predial-rodape.png" alt="Logo" class="logo"/>
            <div class="footer-text"> 
                <div class="footer-contato">
                    <h>CONTATO</h>
                </div>
                <div class="footer-inf">
                    <p>Predial Consultoria LTDA | CNPJ: xxxxxxxx/xxxx-xx | R. Fatec, 04 | São José dos Campos – SP | CEP 00000-000</p>
                    <p>Contato Suporte: (12)00000-0000 E-mail: suporte@predial.com.br</p>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
    import './style.css'
    import { ref } from 'vue';
    import LaudoTecnicoForm from "./LaudoTecnicoView.vue";
    import ChecklistForm from "./ChecklistView.vue";
    import OrdemServicoForm from "./OrdemServicoView.vue";
        
    let detalhesOS = ref(false);
    let detalhesCK = ref(false);
    let detalhesLT = ref(false);
    console.log(detalhesLT)
</script>
  
