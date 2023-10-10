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
        <div style="display: flex; width: 100%; justify-content: end;">
            <InputField style="width: 90%;"
                label="Adicione um item para inspeção:"
                placeholder="Ex.: Inspeção de ar condicionado"
                v-model="checkListItemValue">
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
                    {{ item.item }}
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

const departamentoValue = ref("")
const checkListValue = ref<IListToCheck[]>([])
const checkListItemValue = ref<any>()
const checkListItemToAdd = ref<any>({});
const departamentos = ref<IDepartamento[]>([]);

function listDepartaments() {
  axios.get<any>('http://localhost:8080/departamentos') 
      .then((response: any) => {
            departamentos.value = response.data
      })
      .catch((error: any) => {
          console.error('Erro ao buscar departamentos:', error);
      });
}

function addItemToCheckList(){
    if(checkListItemValue.value){
        checkListItemToAdd.value.item = checkListItemValue.value
        checkListItemToAdd.value.status = 'Não informado'
        checkListItemToAdd.value.comentario = 'Não informado'
        checkListValue.value.push(checkListItemToAdd.value)
        checkListItemValue.value = ''
    }
}

function createCheckList(){
    event?.preventDefault()
    const newCheckList = {
        departamento: { id: departamentoValue.value },
        checklist: checkListValue.value
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