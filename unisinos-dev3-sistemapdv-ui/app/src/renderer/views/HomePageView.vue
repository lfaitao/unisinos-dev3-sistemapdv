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
              <md-button class="md-raised md-primary" @click.native="goTo('/iniciar-venda')">Gerenciar Vendas</md-button>
              <md-button class="md-raised md-primary" @click.native="goTo('/gerir-pre-venda')">Gerenciar Pré-Vendas</md-button>
              <md-button class="md-raised md-primary" @click.native="goTo('/gerir-clientes')">Gerenciar Clientes</md-button>
              <md-button class="md-raised md-primary" @click.native="goTo('/gerir-produtos')">Gerenciar Produtos</md-button>
              <md-button class="md-raised md-primary" @click.native="goTo('/gerir-acessos')">Gerenciar Acessos</md-button>
              <md-button class="md-raised md-primary" @click.native="goTo('/gerir-davs')">Gerenciar DAVs</md-button>
          </md-card>
        </md-layout>
      </md-layout>
    </div>
  </div>
</template>

<script>
    import {router} from '../main'

    export default {
        data() {
            return {
            }
        },
        methods: {
            goTo(route) {
                router.push(route)
            },
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
