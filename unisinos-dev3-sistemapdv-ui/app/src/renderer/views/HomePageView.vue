<template>
    <div>
        <navbar title="Home"></navbar>
        <div class="mainMenu centered_div">
            <md-layout md-gutter md-align="center">
                <md-layout md-column md-align="center" style="align-items: center">
                    <md-card-header style="text-align: center; padding-bottom: 20px">
                        <md-card-header-text>
                            <h1>Sistema PDV</h1>
                        </md-card-header-text>
                    </md-card-header>
                    <md-card md-align="center">
                        <button-auth text="Gerenciar Caixa" path="/gerir-caixa"></button-auth>
                        <button-auth text="Gerenciar Vendas" path="/iniciar-venda" :canNavigateIf="isCaixaAberto && !isCaixaBloqueado" @click.native="verificarCaixaAberto(MOMENTO_CLICANDO) && navigate()"></button-auth>
                        <button-auth text="Gerenciar Pré-Vendas" path="/gerir-pre-venda" :canNavigateIf="!isCaixaBloqueado" @click.native="verificarCaixaBloqueado(MOMENTO_CLICANDO) && navigate()"></button-auth>
                        <button-auth text="Gerenciar Clientes" path="/gerir-clientes" :canNavigateIf="!isCaixaBloqueado" @click.native="verificarCaixaBloqueado(MOMENTO_CLICANDO) && navigate()"></button-auth>
                        <button-auth text="Gerenciar Produtos" path="/gerir-produtos" :canNavigateIf="!isCaixaBloqueado" @click.native="verificarCaixaBloqueado(MOMENTO_CLICANDO) && navigate()"></button-auth>
                        <button-auth text="Gerenciar DAVs" path="/gerir-davs" :canNavigateIf="!isCaixaBloqueado" @click.native="verificarCaixaBloqueado(MOMENTO_CLICANDO) && navigate()"></button-auth>
                        <button-auth text="Gerenciar Acessos" path="/gerir-acessos" :canNavigateIf="!isCaixaBloqueado" @click.native="verificarCaixaBloqueado(MOMENTO_CLICANDO) && navigate()"></button-auth>
                    </md-card>
                </md-layout>
            </md-layout>

            <md-snackbar md-position="bottom center" ref="snackbar" md-duration="4000">
                <span>{{ error }}</span>
                <md-button class="md-accent" md-theme="light-blue" @click.native="$refs.snackbar.close()">
                    OK
                </md-button>
            </md-snackbar>
        </div>
    </div>
</template>

<script>
    import {router} from '../main'
    import {ipcRenderer} from 'electron'
    import buttonAuth from '../components/common/buttonAuth'

    export default {
        components: {
            buttonAuth
        },
        data() {
            return {
                MOMENTO_INICIANDO: 1,
                MOMENTO_CLICANDO: 2,
                isCaixaAberto: false,
                isCaixaBloqueado: false,
                error: ''
            }
        },
        methods: {
            goTo(route) {
                router.push(route)
            },
            openAlert(message) {
                this.error = message
                this.$refs.snackbar.open()
            },
            verificarCaixaAberto(momento) {
                this.isCaixaAberto = ipcRenderer.sendSync('caixa-getAberto');
                this.isCaixaBloqueado = ipcRenderer.sendSync('caixa-getBloqueado');

                if (momento == this.MOMENTO_CLICANDO) {
                    if (!this.isCaixaBloqueado) {
                        if (!this.isCaixaAberto) {
                            this.openAlert("O caixa precisa estar aberto para gerir vendas!")
                        }
                    } else {
                        this.openAlert("O caixa está bloqueado para operações!")
                    }
                }
            },
            verificarCaixaBloqueado(momento) {
                this.isCaixaBloqueado = ipcRenderer.sendSync('caixa-getBloqueado');
                if (momento == this.MOMENTO_CLICANDO) {
                    if (this.isCaixaBloqueado) {
                        this.openAlert("O caixa está bloqueado para operações!")
                    }
                }
            }
        },
        mounted() {
            this.verificarCaixaAberto(this.MOMENTO_INICIANDO)
            this.verificarCaixaBloqueado(this.MOMENTO_INICIANDO)
        },
        name: 'home-page'
    }
</script>

<style scoped>
    .mainMenu {
        background-color: #EEE;
    }

    .centered_div {
        position: absolute;
        top: 20%;
        bottom: 50%;
        left: 50%;
        right: 50%;
    }
</style>
