<template>
    <div class="form-title">
    <h2>Adicionar cliente</h2>
  </div>
  <form action="" class="add-form">
    <div class="input-inline-field">
        <SelectField
            label="Departamento:"
            :option-values="departamentos"
            value-prop="codDepart"
            display-prop="nome"
            v-model="departamentoValue">
        </SelectField>
        <InputField
            label="Nome:"
            placeholder="Informe o nome do checklist"
            v-model="nameValue">
        </InputField>
    </div>
    <div class="input-inline-field">
        <div style="display: flex; width: 100%; justify-content: end;">
            <InputField style="width: 100%;"
                label="Adicione um item para inspeção:"
                placeholder="Ex.: Inspeção de ar condicionado"
                v-model="perguntaValue">
            </InputField>
            <span class="material-symbols-outlined button-check-item" 
                style=""
                @click="addItemToCheckList">
                add
            </span>
        </div>
    </div>

    <div class="checklist-field">
        <ul class="checklist-list">
            <li v-for="(item, index) in checkListValue" :key="index" class="checklist-item">
                <span>
                    {{ item.pergunta }}
                </span>
                <i class="material-symbols-outlined" @click="deleteItemToCheckList(index)">
                    delete
                </i>
            </li>
        </ul>
    </div>
   
    <div class="send-button">
      <InputButton text-button="Salvar" @click="createCheckList" />
    </div>
  </form>
</template>

<script setup lang="ts">
import InputField from '@/components/InputField/InputField.vue';
import InputButton from '@/components/Button/InputButton.vue';
import SelectField from '@/components/Select/SelectField.vue';
import { ref } from 'vue';
import type IDepartamento from '../departamento/IDepartament';
import axios from 'axios';
import { onMounted } from 'vue';
import type ICheckList from './ICheckList';
import type IListToCheck from './IListToCheck';

const nameValue = ref("")
const departamentoValue = ref("")
const checkListValue = ref<IListToCheck[]>([])
const perguntaValue = ref<any>()
const listaPerguntaValue = ref<any>({});
const departamentos = ref<IDepartamento[]>([]);

function listDepartaments() {
  axios.get<any>('http://localhost:8080/departamentos') 
      .then((response: any) => {
            departamentos.value = response.data
            console.log(departamentos.value)
      })
      .catch((error: any) => {
          console.error('Erro ao buscar departamentos:', error);
      });
}

function addItemToCheckList(){
    if(perguntaValue.value){
        const novaPergunta = {
            pergunta: perguntaValue.value,
            status: 'Não informado',
            comentario: 'Não informado'
        };
        checkListValue.value.push(novaPergunta)
        perguntaValue.value = ''
    }
}

function createCheckList(){
    const newCheckList = {
        nome: nameValue.value,
        perguntas: checkListValue.value
    }

    try {
        const response = axios.post("http://localhost:8080/checklist", newCheckList)
        perguntaValue.value = ""
        departamentoValue.value = ""
        console.log(response)
        window.alert("Checklist criado com sucesso")  
        location.reload()      
    } catch (error) {
        console.error('Erro ao criar checklist:', error);
        window.alert("Erro ao criar checklist")
    }
    console.log(newCheckList)
}

function deleteItemToCheckList(index: number){
    if(checkListValue.value.length > 0){
        checkListValue.value.splice(index, 1)
    }
}

onMounted(() => {
    listDepartaments()
})

</script>