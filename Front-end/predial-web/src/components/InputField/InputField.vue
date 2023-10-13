<template>
    <label class="label">
        {{ label }}
        <input 
            class="input-field-component" 
            :type="type"
            :value="modelValue"
            :placeholder="placeholder"
            @input="updateInput"
        >
    </label>       
</template>

<script lang="ts">

    import './style.css'
    import type IListToCheck from '@/views/checklist/IListToCheck';

    export default {
        name: "InputField",
        props: {
            label:{
                type: String, 
                default: "Informe o label"
            },
            type: {
                type: String,
                default: "text"
            },
            modelValue: {
                type: [String, Object],
                default: ""
            },
            placeholder: {
                type: String,
                default: "Informe o placeholder"
            }
        },
        methods: {
            updateInput(event: any) {
                this.$emit("update:modelValue", event.target.value)
            }
        }
    }

    

</script>

<style>
 
</style>