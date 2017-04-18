<template>
  <div>
    <div style="height: 100%; background-color: #EEE;">
      <md-layout md-gutter md-align="center" style="height: 100%">
        <md-layout md-column md-align="center" style="height: 100%; align-items: center">
          <md-card-header style="text-align: center; padding-bottom: 20px">
            <md-card-header-text>
              <h1>Sistema PDV</h1>
            </md-card-header-text>
          </md-card-header>
          <md-card md-align="center" style="background-color: rgba(158, 158, 158, 0.35)">
              <md-button class="md-raised md-primary" @click.native="goTo('/gerir-caixa')">Gerenciar Caixa</md-button>
              <md-button class="md-raised md-primary">Gerenciar Vendas</md-button>
              <md-button class="md-raised md-primary">Gerenciar Pré-Vendas</md-button>
              <md-button class="md-raised md-primary">Gerenciar DAVs</md-button>
              <md-button class="md-raised md-primary">Gerenciar Usuários</md-button>
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
                alert(route);
                router.push(route)
            },
        },
        mounted() {
        },
        name: 'home-page'
    }
</script>

<style scoped>
  img {
    margin-top: -25px;
    width: 450px;
  }
</style>
