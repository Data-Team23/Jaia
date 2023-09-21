<template>
    <div class="div-button">
        <button @click="onClick">{{ textButton }}</button>
    </div>
</template>

<script lang="ts">
    import type { PropType } from 'vue';
    import './styles.css'

    export default {
        name: 'InputButton',
        props: {
            textButton: {
                type: String,
                default: 'Enviar'
            },
            onClick: {
                type: Function as PropType<(event: MouseEvent) => void>,
                default: () => {}
            }
        }
    }
</script>