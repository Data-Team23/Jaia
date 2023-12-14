<template>
    <div class="form-title">
        <h2>Atualizar checklist</h2>
    </div>
    <form action="" class="add-form">
        <div class="input-inline-field">
            <!-- <SelectField label="Departamento:" :option-values="departamentos" value-prop="codDepart" display-prop="nome"
                v-model="departamentoValue" :pre-selected-prop="departamentoPreSelected">
            </SelectField> -->
            <InputField label="Departamento:" placeholder="Informe o departamento" v-model="departamentoValue">
            </InputField>
            <InputField label="Nome:" placeholder="Informe o nome do checklist" v-model="nameValue">
            </InputField>
        </div>
        <div class="input-inline-field">
            <div style="display: flex; width: 100%; justify-content: end;">
                <InputField style="width: 100%;" label="Adicione um item para inspeção:"
                    placeholder="Ex.: Inspeção de ar condicionado" v-model="perguntaValue">
                </InputField>
                <span class="material-symbols-outlined button-check-item" style="" @click="addItemToCheckList">
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
            <InputButton text-button="Salvar" @click="updateCheckList" />
        </div>
    </form>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import InputField from '@/components/InputField/InputField.vue';
import SelectField from '@/components/Select/SelectField.vue'
import InputButton from '@/components/Button/InputButton.vue';
import type IDepartamento from '../departamento/IDepartament';
import type IListToCheck from './IListToCheck';
import axios from 'axios';
import { onMounted } from 'vue';
import router from '@/router';
import type { Router } from 'vue-router';
import type IClient from '../cliente/IClient';
import type ICheckList from './ICheckList';


const departamentoValue = ref<any>()
const departamentoPreSelected = ref<any>()
const nameValue = ref("")
const checkListValue = ref<any[]>([])
const perguntaValue = ref<any>()
const listaPerguntaValue = ref<any>({});
const departamentos = ref<IDepartamento[]>([]);
const checklistSelected = ref<any>()

function addItemToCheckList() {
    if (perguntaValue.value) {
        const novaPergunta = {
            pergunta: perguntaValue.value,
            status: 'Não informado',
            comentario: 'Não informado'
        };
        checkListValue.value.push(novaPergunta)
        perguntaValue.value = ''
    }
}

function deleteItemToCheckList(index: number) {
    if (checkListValue.value.length > 0) {
        checkListValue.value.splice(index, 1)
    }
}

function updateCheckList() {
    console.log("updated")
}

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

onMounted(async () => {
    listDepartaments()
    await waitForIdInRoute(router);
    const checklistId = router.currentRoute.value.query.id;
    try {
        axios.get<ICheckList>(`http://localhost:8080/checklist/${checklistId}`).then((response) => {
            if (response.data) {
                checklistSelected.value = response.data
                nameValue.value = checklistSelected.value.nome
                departamentoValue.value = checklistSelected.value.departamento.nome ?? "Não informado"
                console.log(departamentoValue.value)
                checkListValue.value = checklistSelected.value.perguntas
            }
            console.log(response.data)
        })
    } catch (error) {
        console.log(error)
    }
})

async function waitForIdInRoute(router: Router) {
    // Loop para aguardar até que route.query.cnpj esteja definido
    while (router.currentRoute.value.query.id === undefined) {
        // Definição do período de tempo (pode ser ajustado)
        await new Promise((resolve) => setTimeout(resolve, 100));
    }
}

</script>