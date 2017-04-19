<template>
  <div>
      <navbar title="Gerir Caixa" previousPage="/"></navbar>
        <!--<div style="height: 100%; background-color: #EEE;">-->
          <!--<md-layout md-gutter md-align="center" style="height: 100%">-->
            <!--<md-layout md-column md-align="center" style="height: 100%; align-items: center">-->
              <!--<md-card-header style="text-align: center; padding-bottom: 20px">-->
                <!--<md-card-header-text>-->
                  <!--<h1>Sistema PDV</h1>-->
                <!--</md-card-header-text>-->
              <!--</md-card-header>-->
              <!--<md-card md-align="center" style="background-color: rgba(158, 158, 158, 0.35)">-->
                  <!--<md-button class="md-raised md-primary">Gerenciar Caixa</md-button>-->
              <!--</md-card>-->
            <!--</md-layout>-->
          <!--</md-layout>-->
        <!--</div>-->
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
  /* Vazio */
</style>
