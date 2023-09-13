<template>
    <label for="" class="label">
        {{ label }}
        <select name="" id="" class="select-field">
            <option style="color: #B3B3B3;" selected>Selecione um opção</option>
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
            }
        },
        methods: {
            updateSelect(event: any) {
                this.$emit("update:option", event.target.value)
            }
        }
    }
</script>