<template>
    <md-toolbar>
        <md-button v-if="previousPage" class="md-icon-button" @click.native="goTo(previousPage)">
            <md-icon>arrow_back</md-icon>
        </md-button>

        <h2 class="md-title" style="flex: 1">{{ title }}</h2>

        <md-button class="md-icon-button md-raised" @click.native="logout">
            <md-icon>power_settings_new</md-icon>
            <md-tooltip md-direction="bottom">Logout</md-tooltip>
        </md-button>
    </md-toolbar>
</template>

<script>
    import {router} from '../main'
    import auth from '../views/login/index'

    export default {
        methods: {
            goTo(route) {
                router.push(route)
            },
            logout() {
                auth.logout()
            }
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
