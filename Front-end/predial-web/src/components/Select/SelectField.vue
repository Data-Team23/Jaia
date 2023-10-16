<template>
    <label for="" class="label">
        {{ label }}
        <select name="" id="select-field-comp" class="select-field" :value="modelValue" @input="updateSelect">
            <option v-if="preSelectedProp != undefined" style="color: #B3B3B3;" disabled value="" selected>{{ preSelectedProp }}</option>
            <option v-else selected>Selecione uma opção</option>
            <template v-for="option in optionValues" :key="getSelectedValue(option)">
                <option :value="getSelectedValue(option)">{{getSelectedLabel(option)}}</option>
            </template>
        </select>
    </label>
</template>

<script lang="ts">
    import './style.css'
    export default {
        name: 'SelectField',
        props: {
            label: {
                type: String,
                default: ''
            },
            optionValues: {
                type: Array,
                default: () => []
            },
            modelValue: {
                type: String,
                default: null
            },
            valueProp: {
                type: String,
                default: 'value'
            },
            displayProp: {
                type: String,
                default: 'label'
            },
            preSelectedProp: {
                type: String,
                default: 'Selecione uma opção'
            }
        },
        methods: {
            updateSelect(event: any) {
                const selectedValue = (event.target as HTMLSelectElement).value;
                this.$emit('update:modelValue', selectedValue);
            },
            getSelectedValue(option: any){
                return option[this.valueProp];
            },
            getSelectedLabel(option: any){
                return option[this.displayProp]

            }
        }
    }
</script>