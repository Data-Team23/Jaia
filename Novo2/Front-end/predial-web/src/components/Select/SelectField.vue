<template>
    <label for="" class="label">
        {{ label }}
        <select name="" id="" class="select-field" :value="modelValue" @input="updateSelect">
            <option style="color: #B3B3B3;" disabled value="">Selecione um opção</option>
            <option :value="option"  v-for="option in optionValues">{{option}}</option>
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
                default: 'Selecione uma opção'
            },
            optionValues: {
                type: Array,
                default: () => []
            },
            modelValue: {
                type: String,
                default: null
            }
        },
        methods: {
            updateSelect(event: any) {
                const selectedValue = (event.target as HTMLSelectElement).value;
                this.$emit('update:modelValue', selectedValue);
            }
        }
    }
</script>