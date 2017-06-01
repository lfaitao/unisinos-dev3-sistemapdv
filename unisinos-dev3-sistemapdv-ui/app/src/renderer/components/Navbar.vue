<template>
    <md-toolbar>
        <md-button class="md-icon-button" @click.native="goTo(previousPage)">
            <md-icon>{{previousPage ? "arrow_back" : "home"}}</md-icon>
        </md-button>

        <h2 class="md-title" style="flex: 1">{{ title }}</h2>
        
        <md-button class="md-dense" @click.native="logout()">
            Logoff
        </md-button>
    </md-toolbar>
</template>

<script>
    import {router} from '../main'
    import auth from '../views/login/index'

    export default {
        methods: {
            goTo(route) {
                if(this.previousPage)
                    router.push(route)
            },
            logout(){
                auth.logout();
            }
        },
        props: {
            title: {
                type: String,
                required: true
            },
            previousPage: {
                type: String,
                required: false
            }
        },
        name: 'navbar'
    }
</script>

<style scoped>
    /* Vazio */
</style>
