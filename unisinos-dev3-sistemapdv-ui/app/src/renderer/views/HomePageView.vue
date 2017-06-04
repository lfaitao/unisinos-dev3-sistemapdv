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
            <button-auth text="Gerenciar Caixa" path="/gerir-caixa" />
            <button-auth text="Gerenciar Vendas" path="/iniciar-venda" />
            <button-auth text="Gerenciar Pré-Vendas" path="/gerir-pre-venda" />
            <button-auth text="Gerenciar Clientes" path="/gerir-clientes" />
            <button-auth text="Gerenciar Produtos" path="/gerir-produtos" />
            <button-auth text="Gerenciar DAVs" path="/gerir-davs" />
            <button-auth text="Gerenciar Acessos" path="/gerir-acessos" />
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
         components:{
            buttonAuth
        },
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
            verificarEstadoCaixa() {
                let isCaixaAberto = ipcRenderer.sendSync('caixa-getAberto');
                if (isCaixaAberto) {
                    return true;
                } else {
                    this.openAlert("O caixa precisa estar aberto para gerir vendas!")
                    return false;
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
