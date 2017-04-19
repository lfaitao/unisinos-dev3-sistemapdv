<template>
  <div class="centered_div">
    <div class="mainMenu">
      <md-layout md-gutter md-align="center">
        <md-layout md-column md-align="center" style="align-items: center">
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
  .centered_div {
      position: absolute;
      top:  20%;
      bottom:  50%;
      left: 50%;
      right: 50%;
      /*transform: translate(-50%,-50%);*/
  }
  /*.centered_div {*/
      /*width: Xu;*/
      /*height: Yu;*/
      /*position: absolute;*/
      /*top: 50%;*/
      /*left: 50%;*/
      /*margin-left: -(X/2)u;*/
      /*margin-top: -(Y/2)u;*/
  /*}*/
  /*body{*/
      /*align-items: center;*/
      /*background:*/
              /*radial-gradient(*/
                      /*ellipse at center,*/
                      /*rgba(255, 255, 255, 1) 0%,*/
                      /*rgba(229, 229, 229, .85) 100%*/
              /*);*/
      /*background-position: center;*/
      /*display: flex;*/
      /*font-family: Lato, Helvetica, sans-serif;*/
      /*justify-content: center;*/
      /*text-align: center;*/
  /*}*/
  .mainMenu {
      height: 100%;
      background-color: #EEE;
  }
</style>
