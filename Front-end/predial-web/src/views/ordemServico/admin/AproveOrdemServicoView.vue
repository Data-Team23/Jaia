<template>
  <div class="form-title">
    <h2>Ordem de Serviço</h2>
  </div>
  <form action="" class="add-form">
    <div class="input-inline-field">
      <InputField 
        label="Nome"
        placeholder="Informe o nome"
        v-model="nome_ordemValue"
      ></InputField>
      <InputField 
        label="Status da Requisição" 
        placeholder="Informe o status"
        v-model="statusRValue"
      ></InputField>
    </div>
    <div class="input-inline-field">
      <InputField 
        label="Data Abertura" 
        placeholder="Data da Abertura do chamado"
        v-model="dataaberturaValue"
      ></InputField>
      <InputField 
        label="Descrição" 
        placeholder="Descrição"
        v-model="descricaoValue"
      ></InputField>
    </div>
    <div class="input-inline-field">
      <InputField 
        label="CNPJ" 
        placeholder="CNPJ"
        v-model="cnpjValue"
      ></InputField>
      <InputField 
        label="Status da Ordem de Serviço" 
        placeholder="Informe status da Ordem de Serviço"
        v-model="status_ordemValue"
      ></InputField>
    </div>
    <div class="input-inline-field">
      <InputField 
        label="Tipo de inspeção" 
        placeholder="Inspeção"
        v-model="inspecaoValue"
      ></InputField>
      <InputField 
        label="Responsável" 
        placeholder="Informe Responsável"
        v-model="responsavelValue"
      ></InputField>
    </div>
    <div class="input-inline-field">
      <InputField 
        type="datetime-local"
        label="Data da prestação de Serviço" 
        placeholder="Informe a data"
        v-model="dataValue"
      ></InputField>
      <InputField 
        label="Checklist" 
        placeholder="Informe Checklist"
        v-model="checklValue"
      ></InputField>
    </div>
    
    <div class="input-inline-field" v-for="(pergunta, index) in ordem_servicoSelected?.id_check?.perguntas || []" :key="index">
        <div class="column">
          <label class="input-inline-field" style="width: 445px;">{{ pergunta.pergunta }}</label>
          <select class="input-select" v-model="pergunta.status">
            <option style="color: black;" value="Aprovado">Aprovado</option>
            <option value="Reprovado">Reprovado</option>
          </select>
          <InputField class="input-inline-field" style="width: 445px;"
            label="" 
            placeholder="Comentário"
            v-model="pergunta.comentario"
          ></InputField>
        </div>
      </div>
    
    <div class="send-button"> 
      <InputButton text-button="Aprovar" @click="generatePDFAndSendEmail"></InputButton>
      <InputButton text-button="Reprovar" @click="generatePDFAndSendEmail"></InputButton>
    </div>
    <InputButton text-button="Baixar PDF" @click="generatePDF"></InputButton>
  </form>
</template>

<script setup lang="ts">
import InputField from '@/components/InputField/InputField.vue';
import InputButton from '@/components/Button/InputButton.vue';
import { onMounted, ref } from 'vue';
import { useRouter, type Router } from 'vue-router';
import type IOrdemServico from '../IOrdemServico';
import axios from 'axios';
import router from '@/router';
import pdfMake from "pdfmake/build/pdfmake";
import pdfFonts from "pdfmake/build/vfs_fonts";

pdfMake.vfs = pdfFonts.pdfMake.vfs;

const nome_ordemValue = ref('');
const statusRValue = ref('');
const dataaberturaValue = ref('');
const descricaoValue = ref('');
const cnpjValue = ref('');
const status_ordemValue = ref('');
const inspecaoValue = ref('');
const responsavelValue = ref('');
const dataValue = ref('');
const checklValue = ref('');
const respostas = ref([]);
const ordem_servicoSelected = ref<IOrdemServico | null>(null);

onMounted(async () => {
  await waitForIdInRoute(router);
  const id = router.currentRoute.value.query.id;
  try {
    const response = await axios.get<IOrdemServico>(`http://localhost:8080/ordem-servico/${id}`);
    ordem_servicoSelected.value = response.data;

    ordem_servicoSelected.value = response.data;
            console.log(ordem_servicoSelected.value)
            nome_ordemValue.value = ordem_servicoSelected.value.nome_ordem;
            dataaberturaValue.value = ordem_servicoSelected.value.id_req.data_abertura;
            statusRValue.value = ordem_servicoSelected.value.id_req.status;
            cnpjValue.value = ordem_servicoSelected.value.id_req.fkCliente.cnpj;
            inspecaoValue.value = ordem_servicoSelected.value.tipo_inspecao;
            responsavelValue.value = ordem_servicoSelected.value.fkSupervisor.nome;
            status_ordemValue.value = ordem_servicoSelected.value.status_ordem;
            descricaoValue.value = ordem_servicoSelected.value.id_req.descricao;
            checklValue.value = ordem_servicoSelected.value.id_check.nome;

  } catch (error) {
    console.error(error);
  }

});

async function waitForIdInRoute(router: Router) {
  while (router.currentRoute.value.query.id === undefined) {
    await new Promise((resolve) => setTimeout(resolve, 100));
  }
}

async function getPdfData(): Promise<Blob> {
  const docDefinition = {
    content: [
      { text: 'Nome: ' + nome_ordemValue.value },
      { text: 'Status da Requisição: ' + statusRValue.value },
      { text: 'Data Abertura: ' + dataaberturaValue.value },
      { text: 'Descrição: ' + descricaoValue.value },
      { text: 'CNPJ: ' + cnpjValue.value },
      { text: 'Status da Ordem de Serviço: ' + status_ordemValue.value },
      { text: 'Inspeção: ' + inspecaoValue.value },
      { text: 'Responsável: ' + responsavelValue.value },
      { text: 'Data da prestação de Serviço: ' + dataValue.value },
      { text: 'Checklist: ' + checklValue.value },
    ],
  };

  if (ordem_servicoSelected.value?.id_check?.perguntas) {
    const perguntasContent = ordem_servicoSelected.value.id_check.perguntas.map((pergunta) => {
      const status = pergunta.status;
      const comentario = pergunta.comentario;
      if (status === "Aprovado" || status === "Reprovado") {
        const texto = `Pergunta: ${pergunta.pergunta} - ${status}`;
        if (comentario) {
          docDefinition.content.push({ text: texto, margin: [0, 2], bold: true });
        } else {
          docDefinition.content.push({ text: texto, margin: [0, 2] });
        }
      }
    });

    if (perguntasContent.length > 0) {
      docDefinition.content.push({ text: 'Perguntas e Respostas:', margin: [0, 8], bold: true });
      docDefinition.content.push(...perguntasContent);
    }
  }

  return new Promise((resolve) => {
    const pdfDocument = pdfMake.createPdf(docDefinition);
    pdfDocument.getBlob((blob: Blob) => {
      resolve(blob);
    });
  });
}

async function generatePDFAndSendEmail() {
  try {
    const pdfData = await getPdfData();
    const pdfFileName = 'ordem_servico.pdf';

    const email = ordem_servicoSelected.value?.id_req.fkCliente.email;

    if (typeof email === 'string') {
      const assunto = 'Predial - Seja bem-vindo(a) | Ordem de Serviço';
      const corpo = `<p>Olá, ${ordem_servicoSelected.value?.id_req.fkCliente.nome}! Bem-vindo(a) ao Predial!</p>` +
                   `<p>Sua Ordem de Serviço gerada a partir da Requisição: ${ordem_servicoSelected.value?.id_req.nome} Foi Aprovada <br /></p>` +
                   `<p>Segue em anexo o PDF com mais informações: ${pdfFileName}</p>`;

      const formData = new FormData();
      formData.append('recipient', email);
      formData.append('subject', assunto);
      formData.append('body', corpo);
      formData.append('pdfData', new Blob([pdfData], { type: 'application/pdf' }), pdfFileName);

      const response = await axios.post('http://localhost:8080/email/send', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });
      window.alert('E-mail enviado com sucesso!')
    } else {
      window.alert('Endereço de e-mail inválido.');
    }
  } catch (error) {
    console.error(error);
    window.alert('Erro ao enviar o e-mail.');
  }
}

async function generatePDF() {
  const docDefinition = {
    content: [
      { text: "Nome: " + nome_ordemValue.value },
      { text: "Status da Requisição: " + statusRValue.value },
      { text: "Data Abertura: " + dataaberturaValue.value },
      { text: "Descrição: " + descricaoValue.value },
      { text: "CNPJ: " + cnpjValue.value },
      { text: "Status da Ordem de Serviço: " + status_ordemValue.value },
      { text: "Inspeção: " + inspecaoValue.value },
      { text: "Responsável: " + responsavelValue.value },
      { text: "Data da prestação de Serviço: " + dataValue.value },
      { text: "Checklist: " + checklValue.value },
    ],
  };
  if (ordem_servicoSelected.value?.id_check?.perguntas) {
    const perguntasContent = ordem_servicoSelected.value.id_check.perguntas.map((pergunta) => {
      const status = pergunta.status;
      const comentario = pergunta.comentario;
      if (status === "Aprovado" || status === "Reprovado") {
        const texto = `Pergunta: ${pergunta.pergunta} - ${status}`;
        if (comentario) {
          return { text: texto, margin: [0, 2], bold: true };
        } else {
          return { text: texto, margin: [0, 2] };
        }
      }
      return null;
    }).filter(Boolean); 
    if (perguntasContent.length > 0) {
      docDefinition.content.push({ text: "Perguntas e Respostas:", margin: [0, 8], bold: true });
      docDefinition.content.push(...perguntasContent);
    }
  }
  const pdf = pdfMake.createPdf(docDefinition);
  window.alert("PDF gerado com sucesso!");
  pdf.download("ordem_servico.pdf");
}

</script>
