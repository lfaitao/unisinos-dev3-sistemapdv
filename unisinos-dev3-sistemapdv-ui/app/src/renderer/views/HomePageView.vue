<template>
  <div>
    <navbar title="Main Menu"></navbar>
    <div class="mainMenu centered_div">
      <md-layout md-gutter md-align="center">
        <md-layout md-column md-align="center" style="align-items: center">
          <md-card-header style="text-align: center; padding-bottom: 20px">
            <md-card-header-text>
              <h1>Sistema PDV</h1>
            </md-card-header-text>
          </md-card-header>
          <md-card md-align="center">
              <md-button class="md-raised md-primary" @click.native="goTo('/gerir-caixa')">Gerenciar Caixa</md-button>
              <md-button class="md-raised md-primary" @click.native="goToGerirVendas()">Gerenciar Vendas</md-button>
              <md-button class="md-raised md-primary" @click.native="goTo('/gerir-pre-venda')">Gerenciar Pré-Vendas</md-button>
              <md-button class="md-raised md-primary" @click.native="goTo('/gerir-clientes')">Gerenciar Clientes</md-button>
              <md-button class="md-raised md-primary" @click.native="goTo('/gerir-produtos')">Gerenciar Produtos</md-button>
              <md-button class="md-raised md-primary" @click.native="goTo('/gerir-acessos')">Gerenciar Acessos</md-button>
              <md-button class="md-raised md-primary" @click.native="goTo('/gerir-davs')">Gerenciar DAVs</md-button>
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

    export default {
        data() {
            return {
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
            goToGerirVendas() {
                let isCaixaAberto = ipcRenderer.sendSync('caixa-getAberto');
                if (isCaixaAberto) {
                    router.push('/gerir-venda')
                } else {
                    this.openAlert("O caixa precisa estar aberto para gerir vendas!")
                }
            }
        },
        mounted() {
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
        top:  20%;
        bottom:  50%;
        left: 50%;
        right: 50%;
    }
</style>
