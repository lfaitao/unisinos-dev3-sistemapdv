<template>
    <md-toolbar>
        <md-button class="md-icon-button" @click.native="goTo(previousPage)">

            <md-icon>{{previousPage ? "arrow_back" : "home"}}</md-icon>
        </md-button>

        <h2 class="md-title" style="flex: 1">{{ title }}</h2>

        <!-- Ícone Caixa Aberto/Fechado -->
        <transition name="fade" mode="out-in">
            <div v-if="caixaAberto" style="padding: 5px 10px;">
                <md-icon>phonelink</md-icon>
                <md-tooltip md-direction="bottom">Caixa Aberto</md-tooltip>
            </div>
            <div v-else style="padding: 5px 10px;">
                <md-icon>phonelink_off</md-icon>
                <md-tooltip md-direction="bottom">Caixa Fechado</md-tooltip>
            </div>
        </transition>

        <!-- Ícone Caixa Bloqueado/Desbloqueado -->
        <transition name="fade" mode="out-in">
            <div v-if="caixaBloqueado" style="padding: 5px 10px;">
                <md-icon>lock_outline</md-icon>
                <md-tooltip md-direction="bottom">Caixa Bloqueado</md-tooltip>
            </div>
            <div v-else style="padding: 5px 10px;">
                <md-icon>lock_open</md-icon>
                <md-tooltip md-direction="bottom">Caixa Desbloqueado</md-tooltip>
            </div>
        </transition>

        <md-button class="md-icon-button md-raised" @click.native="logout">
            <md-icon>power_settings_new</md-icon>
            <md-tooltip md-direction="bottom">Logout</md-tooltip>
        </md-button>
    </md-toolbar>
</template>

<script>
    import {ipcRenderer} from 'electron'
    import {router} from '../main'
    import auth from '../views/login/index'

    export default {
        data() {
            return {
                caixaAberto: false,
                caixaBloqueado: false
            }
        },
        methods: {
            goTo(route) {
                if(this.previousPage)
                    router.push(route)
            },
            logout() {
                auth.logout()
            },
            toggleCaixaAbertoIcon() {
                this.caixaAberto = !this.caixaAberto
            },
            toggleCaixaBloqueadoIcon() {
                this.caixaBloqueado = !this.caixaBloqueado
            },
            atualizaCaixaStatus() {
                let isCaixaAberto = ipcRenderer.sendSync('caixa-getAberto')
                if (isCaixaAberto !== null) {
                    this.caixaAberto = isCaixaAberto
                }

                let isCaixaBloqueado = ipcRenderer.sendSync('caixa-getBloqueado')
                if (isCaixaBloqueado !== null) {
                    this.caixaBloqueado = isCaixaBloqueado
                }
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
        mounted() {
            this.atualizaCaixaStatus();
        },
        name: 'navbar'
    }
</script>

<style scoped>
    /* Vazio */
</style>
