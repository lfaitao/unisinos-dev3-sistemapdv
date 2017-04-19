<template>
    <md-toolbar>
        <md-button class="md-icon-button" @click.native="goTo(previousPage)">
            <md-icon>arrow_back</md-icon>
        </md-button>

        <h2 class="md-title" style="flex: 1">{{ title }}</h2>
    </md-toolbar>
</template>

<script>
    import {router} from '../main'

    export default {
        methods: {
            goTo(route) {
                router.push(route)
            },
        },
        props: {
            title: {
                type: String,
                required: true
            },
            previousPage: {
                type: String,
                required: true
            }
        },
        name: 'navbar'
    }
</script>

<style scoped>
    /* Vazio */
</style>
