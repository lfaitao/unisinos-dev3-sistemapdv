<template>
    <div>
        <navbar title="Gerir Caixa" previousPage="/"></navbar>
        <div class="mainMenu centered_div">
            <md-layout md-gutter md-align="center">
                <md-layout>
                    <md-card md-align="center" style="background-color: rgba(200, 200, 200, 0.35)">
                        <md-button class="md-raised md-primary" @click.native="">Abrir Caixa</md-button>
                        <md-button class="md-raised md-primary" @click.native="">Fechar Caixa</md-button>
                        <md-button class="md-raised md-primary" @click.native="">Bloquear Caixa</md-button>
                        <md-button class="md-raised md-primary" @click.native="">Desbloquear Caixa</md-button>
                        <md-button class="md-raised md-primary" @click.native="">Realizar Sangria</md-button>
                        <md-button class="md-raised md-primary" @click.native="">Realizar Suprimento</md-button>
                        <md-button class="md-raised md-primary" @click.native="">Abrir Dia Fiscal (ECF)</md-button>
                        <md-button class="md-raised md-primary" @click.native="">Fechar Dia Fiscal (ECF)</md-button>
                    </md-card>
                </md-layout>
            </md-layout>
        </div>
    </div>
</template>

<script>
  export default {
      data() {
          return {
              title: 'Gerir Caixa'
          }
      },
      methods: {
          goTo(route) {
              router.push(route)
          },
      },
      mounted() {
      },
      name: 'gerir-caixa'
  }
</script>

<style scoped>
    .mainMenu {
        background-color: #EEE;
    }
    .centered_div {
        position: absolute;
        top:  22%;
        bottom:  50%;
        left: 50%;
        right: 50%;
    }
</style>
