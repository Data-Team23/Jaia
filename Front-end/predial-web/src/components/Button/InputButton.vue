<template>
    <div class="div-button">
        <button>{{ textButton }}</button>
    </div>
</template>

<script lang="ts">
    import './styles.css'

    export default {
        name: 'InputButton',
        props: {
            textButton: {
                type: String,
                default: 'Enviar'
            }
        }
    }
</script>